package com.top.wallet.transaction.application.port.out.persistence;

import com.top.wallet.transaction.domain.Balance;

public interface BalancePersistencePort {
    default Balance retrieve(Integer userId){
        throw new UnsupportedOperationException();
    }
}
