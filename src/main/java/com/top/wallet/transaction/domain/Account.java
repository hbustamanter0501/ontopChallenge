package com.top.wallet.transaction.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Account {

    String name;
    String lastName;
    String accountNumber;
    String routingNumber;
    String nationalIdentificationNumber;
    String bankName;
    Integer userId;
}
