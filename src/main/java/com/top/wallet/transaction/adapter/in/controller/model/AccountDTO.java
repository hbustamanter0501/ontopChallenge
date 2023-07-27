package com.top.wallet.transaction.adapter.in.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

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

}
