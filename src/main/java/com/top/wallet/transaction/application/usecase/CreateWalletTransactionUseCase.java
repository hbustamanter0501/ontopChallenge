package com.top.wallet.transaction.application.usecase;

import com.top.wallet.transaction.adapter.in.controller.model.WalletTransactionDTO;
import com.top.wallet.transaction.application.exception.NoBalanceException;
import com.top.wallet.transaction.application.exception.InvalidValueException;
import com.top.wallet.transaction.application.port.in.CreateWalletTransactionCommand;
import com.top.wallet.transaction.application.port.out.persistence.WalletTransactionPersistencePort;
import com.top.wallet.transaction.application.port.out.rest.BalanceRestAdapterPort;
import com.top.wallet.transaction.application.port.out.rest.PaymentProviderRestAdapterPort;
import com.top.wallet.transaction.application.port.out.rest.WalletTransactionRestAdapterPort;
import com.top.wallet.transaction.config.ErrorCode;
import com.top.wallet.transaction.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

import static java.lang.Math.abs;

@Slf4j
@Component
public class CreateWalletTransactionUseCase implements CreateWalletTransactionCommand {

    private final WalletTransactionRestAdapterPort walletTransactionRestAdapter;
    private final BalanceRestAdapterPort balanceRestAdapter;
    private final WalletTransactionPersistencePort walletTransactionPersistence;
    private final PaymentProviderRestAdapterPort paymentProviderRestAdapter;
    private final Double ontopTransactionFee;

    public CreateWalletTransactionUseCase(
            WalletTransactionRestAdapterPort walletTransactionRestAdapter,
            BalanceRestAdapterPort balanceRestAdapter,
            WalletTransactionPersistencePort walletTransactionPersistence,
            PaymentProviderRestAdapterPort paymentProviderRestAdapter,
            @Value("${ontop.transaction.fee}") Double ontopTransactionFee){
        this.walletTransactionRestAdapter = walletTransactionRestAdapter;
        this.balanceRestAdapter = balanceRestAdapter;
        this.walletTransactionPersistence = walletTransactionPersistence;
        this.paymentProviderRestAdapter = paymentProviderRestAdapter;
        this.ontopTransactionFee = ontopTransactionFee;
    }

    @Override
    public WalletTransaction execute(WalletTransactionDTO dto) throws RuntimeException {
        if (Objects.isNull(dto.getAmount()) || Objects.isNull(dto.getUserId())){
            throw new InvalidValueException(ErrorCode.NULL_VALUE);
        }
        if(Objects.equals(dto.getAmount(), (double)0)){
            throw new InvalidValueException(ErrorCode.AMOUNT_VALUE_NOT_VALID);
        }

        Balance balance = this.balanceRestAdapter.retrieveBalance(dto.getUserId());

        if (!hasBalanceForTransaction(balance.getBalance(), dto.getAmount())){
            throw new NoBalanceException(ErrorCode.NO_BALANCE_FOR_TRANSACTION);
        }

        WalletTransaction walletTransaction = this.walletTransactionRestAdapter.createTransaction(dto);

        walletTransaction = walletTransaction.withStatus(TransactionStatus.CREATED.toString());
        walletTransaction = walletTransaction.withCreationDate(new Date());
        walletTransaction = this.walletTransactionPersistence.save(walletTransaction);

        if (Objects.equals(walletTransaction.getTransactionType(), TransactionType.WITHDRAW.toString())){
            //Fee and payment in provider are only for Withdraw, Topups are internal and without fee

            PaymentProvider paymentProvider = this.paymentProviderRestAdapter.createPaymentInProvider(dto);
            walletTransaction = walletTransaction.withFee(abs(dto.getAmount())*ontopTransactionFee);
            walletTransaction = walletTransaction.withStatus(paymentProvider.getStatus().toUpperCase());
            walletTransaction = walletTransaction.withProviderId(paymentProvider.getProviderTransactionId());

        }
        if (Objects.equals(walletTransaction.getTransactionType(), TransactionType.TOPUP.toString())){
            //No fee on topups

            walletTransaction = walletTransaction.withFee((double) 0);
            walletTransaction = walletTransaction.withStatus(TransactionStatus.COMPLETED.toString());
        }
        walletTransaction = this.walletTransactionPersistence.update(walletTransaction);

        return walletTransaction;
    }

    private boolean hasBalanceForTransaction (Integer balance, Double amount){
        return (double)balance + amount >= 0;
    }





}
