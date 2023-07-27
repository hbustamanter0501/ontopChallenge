package com.top.wallet.transaction.adapter.out.rest.paymentProvider.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountRequest {

    String accountNumber;
    String routingNumber;
    String currency;
    String name;
}
