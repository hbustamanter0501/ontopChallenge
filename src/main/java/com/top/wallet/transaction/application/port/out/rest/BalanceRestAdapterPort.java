package com.top.wallet.transaction.application.port.out.rest;

import com.top.wallet.transaction.domain.Balance;

public interface BalanceRestAdapterPort {
    default Balance retrieveBalance(Integer userId){
        throw new UnsupportedOperationException();
    }

}
