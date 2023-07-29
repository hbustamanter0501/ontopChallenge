package com.top.wallet.transaction.adapter.out.repository;

import com.top.wallet.transaction.adapter.out.entity.WalletTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WalletTransactionRepository extends JpaRepository<WalletTransactionEntity, Integer>,
        JpaSpecificationExecutor<WalletTransactionEntity> {


}
