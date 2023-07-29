package com.top.wallet.transaction.application.port.in;

import com.top.wallet.transaction.adapter.in.controller.model.WalletTransactionRestModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.Optional;

public interface GetWalletTransactionsCommand {

    default Page<WalletTransactionRestModel> execute(Integer userId, Optional<Double> amount, Optional<Date> date, Pageable pageable) throws RuntimeException {
        throw new UnsupportedOperationException();
    }
}
