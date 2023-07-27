package com.top.wallet.transaction.domain

import spock.lang.Specification

class TransactionTypeTest extends Specification {

    def "given different transaction types when the amount is OK then do not throw exception"() {
        given:
        def walletTransaction = WalletTransaction.builder()
                .amount(amount)
                .build()

        when:
        type = TransactionType.getType(walletTransaction.getAmount())

        then:
        notThrown(Exception)

        where:
        amount | type
        200    | TransactionType.TOPUP
        450    | TransactionType.TOPUP
        15000  | TransactionType.TOPUP
        -200   | TransactionType.WITHDRAW
        -450   | TransactionType.WITHDRAW
        -15000 | TransactionType.WITHDRAW
    }
}