package com.top.wallet.transaction.application.port.out.rest;

import com.top.wallet.transaction.adapter.in.controller.model.WalletTransactionDTO;
import com.top.wallet.transaction.domain.WalletTransaction;

public interface WalletTransactionRestAdapterPort {
    default WalletTransaction createTransaction(WalletTransactionDTO walletTransaction){
        throw new UnsupportedOperationException();
    }
}
