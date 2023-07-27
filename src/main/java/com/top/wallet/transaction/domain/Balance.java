package com.top.wallet.transaction.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Balance {

    Integer balance;
    Integer userId;

}
