package com.top.wallet.transaction.adapter.in.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletTransactionCommand {

    private Integer id;
    private Double amount;
    private Integer userId;
    private Double fee;
    private Integer transactionId;
    private String transactionType;
    private String status;
    private String providerId;

}
