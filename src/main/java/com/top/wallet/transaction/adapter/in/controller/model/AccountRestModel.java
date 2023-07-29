package com.top.wallet.transaction.adapter.in.controller.model;

import com.top.wallet.transaction.domain.Account;
import com.top.wallet.transaction.domain.WalletTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRestModel {

    private String name;
    private String lastName;
    private String accountNumber;
    private String routingNumber;
    private String nationalIdentificationNumber;
    private String bankName;
    private Integer userId;

    public String getFullName() {
        return getName() + " " + getLastName();
    }

    public static AccountRestModel fromDomain(Account account){
        return AccountRestModel.builder()
                .name(account.getName())
                .lastName(account.getLastName())
                .accountNumber(account.getAccountNumber())
                .routingNumber(account.getRoutingNumber())
                .nationalIdentificationNumber(account.getNationalIdentificationNumber())
                .bankName(account.getBankName())
                .userId(account.getUserId())
                .build();
    }

}
