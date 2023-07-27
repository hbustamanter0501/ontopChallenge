package com.top.wallet.transaction.application.port.out.rest;

import com.top.wallet.transaction.adapter.in.controller.model.WalletTransactionDTO;
import com.top.wallet.transaction.domain.PaymentProvider;

public interface PaymentProviderRestAdapterPort {
    default PaymentProvider createPaymentInProvider(WalletTransactionDTO dto){
        throw new UnsupportedOperationException();
    }

}
