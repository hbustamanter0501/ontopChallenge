package com.top.wallet.transaction.adapter.out.persistence;

import com.top.wallet.transaction.adapter.out.entity.WalletTransactionEntity;
import com.top.wallet.transaction.adapter.out.repository.WalletTransactionRepository;
import com.top.wallet.transaction.application.exception.InvalidValueException;
import com.top.wallet.transaction.application.port.out.persistence.WalletTransactionPersistencePort;
import com.top.wallet.transaction.config.ErrorCode;
import com.top.wallet.transaction.domain.WalletTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class WalletTransactionPersistence implements WalletTransactionPersistencePort {

    private final WalletTransactionRepository walletTransactionRepository;

    @Override
    public WalletTransaction save (WalletTransaction walletTransaction) {
        WalletTransactionEntity walletTransactionEntity = WalletTransactionEntity.fromDomain(walletTransaction);
        walletTransactionEntity = this.walletTransactionRepository.save(walletTransactionEntity);
        return WalletTransactionEntity.toDomain(walletTransactionEntity);
    }

    @Override
    public WalletTransaction update (WalletTransaction walletTransaction) {
        if (Objects.isNull(walletTransaction.getId())){
            throw new InvalidValueException(ErrorCode.NULL_VALUE);
        }
        return this.save(walletTransaction);
    }
}
