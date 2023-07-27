package com.top.wallet.transaction.application.port.out.persistence;

import com.top.wallet.transaction.adapter.in.controller.model.AccountDTO;
import com.top.wallet.transaction.adapter.out.rest.paymentProvider.model.AccountRequest;
import com.top.wallet.transaction.domain.Account;

public interface AccountPersistencePort {
    default AccountDTO findByUserId(Integer userId){
        throw new UnsupportedOperationException();
    }

    default Account save(AccountDTO dto){
        throw new UnsupportedOperationException();
    }
}
