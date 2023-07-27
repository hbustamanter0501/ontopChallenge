package com.top.wallet.transaction.adapter.out.repository;

import com.top.wallet.transaction.adapter.out.entity.WalletTransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface WalletTransactionRepository extends CrudRepository<WalletTransactionEntity, Integer> {

}
