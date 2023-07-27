package com.top.wallet.transaction.adapter.out.persistence;

import com.top.wallet.transaction.adapter.in.controller.model.AccountDTO;
import com.top.wallet.transaction.adapter.out.entity.AccountEntity;
import com.top.wallet.transaction.adapter.out.entity.WalletTransactionEntity;
import com.top.wallet.transaction.adapter.out.repository.AccountRepository;
import com.top.wallet.transaction.adapter.out.rest.paymentProvider.model.AccountRequest;
import com.top.wallet.transaction.application.port.out.persistence.AccountPersistencePort;
import com.top.wallet.transaction.domain.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class AccountPersistence implements AccountPersistencePort {

    private final AccountRepository accountRepository;

    public AccountPersistence (AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO findByUserId (Integer userId) {
        Optional<AccountEntity> contractor = this.accountRepository.findByUserId(userId);
        return contractor.map(AccountEntity::toDto).orElse(null);
    }

    @Override
    public Account save (AccountDTO dto){
        AccountEntity accountEntity = AccountEntity.toEntity(dto);
        accountEntity = this.accountRepository.save(accountEntity);
        return AccountEntity.toDomain(accountEntity);
    }

}
