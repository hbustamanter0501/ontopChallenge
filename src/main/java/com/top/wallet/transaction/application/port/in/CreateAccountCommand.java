package com.top.wallet.transaction.application.port.in;

import com.top.wallet.transaction.adapter.in.controller.model.AccountDTO;
import com.top.wallet.transaction.adapter.in.controller.model.WalletTransactionDTO;
import com.top.wallet.transaction.domain.Account;
import com.top.wallet.transaction.domain.WalletTransaction;

public interface CreateAccountCommand {

    default Account execute(AccountDTO dto) throws RuntimeException {
        throw new UnsupportedOperationException();
    }
}
