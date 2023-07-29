package com.top.wallet.transaction.adapter.in.controller.model;

import com.top.wallet.transaction.adapter.out.entity.WalletTransactionEntity;
import com.top.wallet.transaction.domain.WalletTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletTransactionRestModel {

    final static SimpleDateFormat formatter = new SimpleDateFormat(
            "dd-MM-yyyy");

    private Integer id;
    private Double amount;
    private Integer userId;
    private Double fee;
    private Integer transactionId;
    private String transactionType;
    private String status;
    private String providerId;
    private String creationDate;

    public static WalletTransactionRestModel fromDomain (WalletTransaction walletTransaction){
        return WalletTransactionRestModel.builder()
                .id(walletTransaction.getId())
                .amount(walletTransaction.getAmount())
                .userId(walletTransaction.getUserId())
                .fee(walletTransaction.getFee())
                .transactionId(walletTransaction.getTransactionId())
                .transactionType(walletTransaction.getTransactionType())
                .status(walletTransaction.getStatus())
                .providerId(walletTransaction.getProviderId())
                .creationDate(formatter.format(walletTransaction.getCreationDate()))
                .build();
    }

    public static WalletTransactionRestModel fromEntity (WalletTransactionEntity walletTransaction) {
        return WalletTransactionRestModel.builder()
                .id(walletTransaction.getId())
                .amount(walletTransaction.getAmount())
                .userId(walletTransaction.getUserId())
                .fee(walletTransaction.getFee())
                .transactionId(walletTransaction.getTransactionId())
                .transactionType(walletTransaction.getTransactionType())
                .status(walletTransaction.getStatus())
                .providerId(walletTransaction.getProviderId())
                .creationDate(formatter.format(walletTransaction.getCreationDate()))
                .build();
    }



}
