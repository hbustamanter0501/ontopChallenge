package com.top.wallet.transaction.application.usecase

import com.top.wallet.transaction.adapter.in.controller.model.WalletTransactionDTO
import com.top.wallet.transaction.application.exception.InvalidValueException
import com.top.wallet.transaction.application.exception.NoBalanceException
import com.top.wallet.transaction.application.port.out.persistence.WalletTransactionPersistencePort
import com.top.wallet.transaction.application.port.out.rest.BalanceRestAdapterPort
import com.top.wallet.transaction.application.port.out.rest.PaymentProviderRestAdapterPort
import com.top.wallet.transaction.application.port.out.rest.WalletTransactionRestAdapterPort
import com.top.wallet.transaction.domain.Balance
import com.top.wallet.transaction.domain.PaymentProvider
import com.top.wallet.transaction.domain.TransactionStatus
import com.top.wallet.transaction.domain.TransactionType
import com.top.wallet.transaction.domain.WalletTransaction
import spock.lang.Specification

class CreateWalletTransactionUseCaseTest extends Specification {

    WalletTransactionRestAdapterPort walletTransactionRestAdapter = Mock();
    BalanceRestAdapterPort balanceRestAdapter = Mock();
    WalletTransactionPersistencePort walletTransactionPersistence = Mock();
    PaymentProviderRestAdapterPort paymentProviderRestAdapter = Mock();

    CreateWalletTransactionUseCase target = new CreateWalletTransactionUseCase(
            walletTransactionRestAdapter,
            balanceRestAdapter,
            walletTransactionPersistence,
            paymentProviderRestAdapter,
            0.1)

    def "Given execute is called on CreateWalletTransactionUseCase return an entity of WalletTransaction"(){
        given:
        def userId = 1000
        def dto = WalletTransactionDTO.builder()
                .amount(amount)
                .userId(userId)
                .build()

        def balance = Balance.builder()
                .userId(userId)
                .balance(2500)
                .build()

        def walletTransaction = WalletTransaction.builder()
                .userId(userId)
                .amount(amount)
                .transactionId(111)
                .transactionType(transaction_type)
                .build()

        def paymentProvider = PaymentProvider.builder()
                .amount(amount*(1-0.1))
                .status(TransactionStatus.PROCESSING.toString())
                .providerTransactionId("1111")

        def createdWalletTransaction = walletTransaction.withStatus(TransactionStatus.CREATED.toString()).withId(id)
        def completedWalletTransaction = walletTransaction.withStatus(new_status).withFee(fee)


        when:
        def response = target.execute(dto)

        then:
        1 * balanceRestAdapter.retrieveBalance(userId) >> balance
        1 * walletTransactionRestAdapter.createTransaction(dto) >> walletTransaction
        1 * walletTransactionPersistence.save(walletTransaction) >> createdWalletTransaction
        1 * walletTransactionPersistence.update(createdWalletTransaction) >> completedWalletTransaction
        1 * paymentProviderRestAdapter.createPaymentInProvider(dto) >> paymentProvider
        response == completedWalletTransaction

        where:
        id  |   amount  |   transaction_type                    |   new_status                              |   fee
        1   |   200     |   TransactionType.TOPUP.toString()    |   TransactionStatus.COMPLETED.toString()  |   (double)0
        2   |   -200    |   TransactionType.WITHDRAW.toString() |   TransactionStatus.PROCESSING.toString() |   20
    }

    def "given a wallet transaction without user id then throw error"() {
        given:
        def dto = WalletTransactionDTO.builder()
                .amount(1000)
                .build()

        when:
        target.execute(dto)

        then:
        thrown(InvalidValueException)
    }

    def "given a wallet transaction with 0 as amount then throw error"() {
        given:
        def dto = WalletTransactionDTO.builder()
                .amount(0)
                .userId(1000)
                .build()

        when:
        target.execute(dto)

        then:
        thrown(InvalidValueException)
    }

    def "given a wallet transaction with no balance  then throw error"() {
        given:
        def userId = 1000
        def dto = WalletTransactionDTO.builder()
                .amount(-3500)
                .userId(userId)
                .build()

        def balance = Balance.builder()
                .userId(userId)
                .balance(2500)
                .build()

        when:
        target.execute(dto)

        then:
        1 * balanceRestAdapter.retrieveBalance(userId) >> balance
        thrown(NoBalanceException)
    }

}
