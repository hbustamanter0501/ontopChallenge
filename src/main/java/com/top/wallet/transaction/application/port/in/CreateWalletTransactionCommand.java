package com.top.wallet.transaction.application.port.in;

import com.top.wallet.transaction.adapter.in.controller.model.WalletTransactionDTO;
import com.top.wallet.transaction.domain.WalletTransaction;

public interface CreateWalletTransactionCommand {

    default WalletTransaction execute(WalletTransactionDTO dto) throws RuntimeException {
        throw new UnsupportedOperationException();
    }
}
