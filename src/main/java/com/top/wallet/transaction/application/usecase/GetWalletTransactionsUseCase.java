package com.top.wallet.transaction.application.usecase;

import com.top.wallet.transaction.adapter.in.controller.model.WalletTransactionRestModel;
import com.top.wallet.transaction.adapter.out.entity.WalletTransactionEntity;
import com.top.wallet.transaction.application.exception.InvalidValueException;
import com.top.wallet.transaction.application.port.in.GetWalletTransactionsCommand;
import com.top.wallet.transaction.application.port.out.persistence.AccountPersistencePort;
import com.top.wallet.transaction.application.port.out.persistence.WalletTransactionPersistencePort;
import com.top.wallet.transaction.config.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
public class GetWalletTransactionsUseCase implements GetWalletTransactionsCommand {

    private final WalletTransactionPersistencePort walletTransactionPersistence;


    public GetWalletTransactionsUseCase(WalletTransactionPersistencePort walletTransactionPersistence){
        this.walletTransactionPersistence = walletTransactionPersistence;
    }

    @Override
    public Page<WalletTransactionRestModel> execute(Integer userId, Optional<Double> amount, Optional<Date> date, Pageable pageable) throws RuntimeException {

        Specification<WalletTransactionEntity> filter = null;
        if(amount.isPresent())
            filter = WalletTransactionEntity.specAmount(amount.get());

        if(date.isPresent())
            if(Objects.nonNull(filter))
                filter = filter.and(WalletTransactionEntity.specDate(date.get()));
            else
                filter = WalletTransactionEntity.specDate(date.get());

        if(Objects.nonNull(filter))
            filter = filter.and(WalletTransactionEntity.specUserId(userId));
        else
            filter = WalletTransactionEntity.specUserId(userId);


        return walletTransactionPersistence.findAllByFilters(filter, pageable);
    }





}
