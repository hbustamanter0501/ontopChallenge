package com.top.wallet.transaction.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@Builder
public class WalletTransaction {
    @With
    Integer id;
    Double amount;
    Integer userId;
    @With
    Double fee;
    Integer transactionId;
    String transactionType;
    @With
    String status;
    @With
    String providerId;
}
