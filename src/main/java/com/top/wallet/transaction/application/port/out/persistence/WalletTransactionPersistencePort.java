package com.top.wallet.transaction.application.port.out.persistence;


import com.top.wallet.transaction.domain.WalletTransaction;

public interface WalletTransactionPersistencePort {

    default WalletTransaction save (WalletTransaction walletTransaction){
        throw new UnsupportedOperationException();
    }

    default WalletTransaction update (WalletTransaction walletTransaction){
        throw new UnsupportedOperationException();
    }

}
