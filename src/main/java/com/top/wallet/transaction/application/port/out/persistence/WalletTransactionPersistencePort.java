package com.top.wallet.transaction.application.port.out.persistence;


import com.top.wallet.transaction.adapter.in.controller.model.WalletTransactionRestModel;
import com.top.wallet.transaction.adapter.out.entity.WalletTransactionEntity;
import com.top.wallet.transaction.domain.WalletTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public interface WalletTransactionPersistencePort {

    default WalletTransaction save (WalletTransaction walletTransaction){
        throw new UnsupportedOperationException();
    }

    default WalletTransaction update (WalletTransaction walletTransaction){
        throw new UnsupportedOperationException();
    }

    default Page<WalletTransactionRestModel> findAllByFilters(
            Specification<WalletTransactionEntity> filter,
            Pageable pageable) {
        throw new UnsupportedOperationException();
    }

}
