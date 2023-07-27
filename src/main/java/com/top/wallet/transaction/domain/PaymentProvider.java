package com.top.wallet.transaction.domain;

import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class PaymentProvider {

    String status;
    Double amount;
    String providerTransactionId;

}
