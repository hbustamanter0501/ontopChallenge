package com.top.wallet.transaction.application.usecase;

import com.top.wallet.transaction.adapter.in.controller.model.AccountDTO;
import com.top.wallet.transaction.adapter.in.controller.model.WalletTransactionDTO;
import com.top.wallet.transaction.application.exception.InvalidValueException;
import com.top.wallet.transaction.application.exception.NoBalanceException;
import com.top.wallet.transaction.application.port.in.CreateAccountCommand;
import com.top.wallet.transaction.application.port.in.CreateWalletTransactionCommand;
import com.top.wallet.transaction.application.port.out.persistence.AccountPersistencePort;
import com.top.wallet.transaction.application.port.out.persistence.WalletTransactionPersistencePort;
import com.top.wallet.transaction.application.port.out.rest.BalanceRestAdapterPort;
import com.top.wallet.transaction.application.port.out.rest.PaymentProviderRestAdapterPort;
import com.top.wallet.transaction.application.port.out.rest.WalletTransactionRestAdapterPort;
import com.top.wallet.transaction.config.ErrorCode;
import com.top.wallet.transaction.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Objects;

import static java.lang.Math.abs;

@Slf4j
@Component
public class CreateAccountUseCase implements CreateAccountCommand {

    private final AccountPersistencePort accountPersistencePort;


    public CreateAccountUseCase(AccountPersistencePort accountPersistencePort){
        this.accountPersistencePort = accountPersistencePort;

    }

    @Override
    public Account execute(AccountDTO dto) throws RuntimeException {
       return accountPersistencePort.save(dto);
    }



}
