package com.top.wallet.transaction.adapter.out.rest.balance.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.top.wallet.transaction.domain.Balance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
public class BalanceResponse {

    private Integer balance;
    private Integer userId;

    public Balance toDomain () {
        return Balance.builder()
                .balance(balance)
                .userId(userId)
                .build();
    }
}
