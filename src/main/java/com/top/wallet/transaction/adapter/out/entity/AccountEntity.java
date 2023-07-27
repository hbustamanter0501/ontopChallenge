package com.top.wallet.transaction.adapter.out.entity;

import com.top.wallet.transaction.adapter.in.controller.model.AccountDTO;
import com.top.wallet.transaction.adapter.out.rest.paymentProvider.model.AccountRequest;
import com.top.wallet.transaction.domain.Account;
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
@Table(name = "account")
public class AccountEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastName;
    private String accountNumber;
    private String routingNumber;
    private String nationalIdentificationNumber;
    private String bankName;
    private Integer userId;



    public static AccountDTO toDto (AccountEntity account){
        return AccountDTO.builder()
                .name(account.getName())
                .lastName(account.getLastName())
                .routingNumber(account.getRoutingNumber())
                .accountNumber(account.getAccountNumber())
                .nationalIdentificationNumber(account.getNationalIdentificationNumber())
                .bankName(account.getBankName())
                .userId(account.getUserId())
                .build();
    }

    public static AccountEntity toEntity (AccountDTO account){
        return AccountEntity.builder()
                .name(account.getName())
                .lastName(account.getLastName())
                .routingNumber(account.getRoutingNumber())
                .accountNumber(account.getAccountNumber())
                .nationalIdentificationNumber(account.getNationalIdentificationNumber())
                .bankName(account.getBankName())
                .userId(account.getUserId())
                .build();
    }

    public static Account toDomain (AccountEntity account){
        return Account.builder()
                .name(account.getName())
                .lastName(account.getLastName())
                .routingNumber(account.getRoutingNumber())
                .accountNumber(account.getAccountNumber())
                .nationalIdentificationNumber(account.getNationalIdentificationNumber())
                .bankName(account.getBankName())
                .userId(account.getUserId())
                .build();
    }


}
