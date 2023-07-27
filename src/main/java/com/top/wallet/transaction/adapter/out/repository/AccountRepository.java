package com.top.wallet.transaction.adapter.out.repository;

import com.top.wallet.transaction.adapter.out.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<AccountEntity, Integer> {

    public Optional<AccountEntity> findByUserId(Integer userId);
}
