package com.top.wallet.transaction.adapter.out.entity;

import com.top.wallet.transaction.domain.WalletTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wallet_transaction")
public class WalletTransactionEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double amount;
    private Integer userId;
    private Double fee;
    private Integer transactionId;
    private String transactionType;
    private String status;
    private String providerId;


    public static WalletTransactionEntity fromDomain (WalletTransaction walletTransaction){
        return WalletTransactionEntity.builder()
                .id(walletTransaction.getId())
                .amount(walletTransaction.getAmount())
                .userId(walletTransaction.getUserId())
                .fee(walletTransaction.getFee())
                .transactionId(walletTransaction.getTransactionId())
                .transactionType(walletTransaction.getTransactionType())
                .status(walletTransaction.getStatus())
                .providerId(walletTransaction.getProviderId())
                .build();
    }

    public static WalletTransaction toDomain (WalletTransactionEntity walletTransactionEntity){
        return WalletTransaction.builder()
                .id(walletTransactionEntity.getId())
                .amount(walletTransactionEntity.getAmount())
                .userId(walletTransactionEntity.getUserId())
                .fee(walletTransactionEntity.getFee())
                .transactionId(walletTransactionEntity.getTransactionId())
                .transactionType(walletTransactionEntity.getTransactionType())
                .status(walletTransactionEntity.getStatus())
                .providerId(walletTransactionEntity.getProviderId())
                .build();
    }
}
