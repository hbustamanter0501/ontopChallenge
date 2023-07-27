package com.top.wallet.transaction.domain;

public enum TransactionType {
    TOPUP,
    WITHDRAW;

    public static TransactionType getType (Double amount){
        if (amount < 0){
            return WITHDRAW;
        }

        return TOPUP;
    }
}
