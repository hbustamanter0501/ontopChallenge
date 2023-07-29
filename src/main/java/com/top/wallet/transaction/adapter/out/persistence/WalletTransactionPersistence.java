package com.top.wallet.transaction.adapter.out.persistence;

import com.top.wallet.transaction.adapter.in.controller.model.WalletTransactionRestModel;
import com.top.wallet.transaction.adapter.out.entity.WalletTransactionEntity;
import com.top.wallet.transaction.adapter.out.repository.WalletTransactionRepository;
import com.top.wallet.transaction.application.exception.InvalidValueException;
import com.top.wallet.transaction.application.port.out.persistence.WalletTransactionPersistencePort;
import com.top.wallet.transaction.config.ErrorCode;
import com.top.wallet.transaction.domain.WalletTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class WalletTransactionPersistence implements WalletTransactionPersistencePort {

    private final WalletTransactionRepository walletTransactionRepository;

    @Override
    public WalletTransaction save (WalletTransaction walletTransaction) {
        WalletTransactionEntity walletTransactionEntity = WalletTransactionEntity.toEntity(walletTransaction);
        walletTransactionEntity = this.walletTransactionRepository.save(walletTransactionEntity);
        return WalletTransactionEntity.toDomain(walletTransactionEntity);
    }

    @Override
    public WalletTransaction update (WalletTransaction walletTransaction) {
        if (Objects.isNull(walletTransaction.getId())){
            throw new InvalidValueException(ErrorCode.NULL_VALUE);
        }
        walletTransaction = walletTransaction.withUpdateDate(new Date());
        return this.save(walletTransaction);
    }

    @Override
    public Page<WalletTransactionRestModel> findAllByFilters(
            Specification<WalletTransactionEntity> filter,
            Pageable pageable) throws ServiceException {

        if(filter != null)
            return convertFormWalletTransaction(this.walletTransactionRepository.findAll(filter,pageable));
        else
            return convertFormWalletTransaction(this.walletTransactionRepository.findAll(pageable));

    }

    private Page<WalletTransactionRestModel> convertFormWalletTransaction(Page<WalletTransactionEntity> paginatedEntity) {
        return paginatedEntity.map(WalletTransactionRestModel::fromEntity);
    }
}
