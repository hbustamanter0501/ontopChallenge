package com.top.wallet.transaction.adapter.out.rest.walletTransaction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.top.wallet.transaction.application.exception.InternalServerErrorException;
import com.top.wallet.transaction.config.ErrorCode;
import com.top.wallet.transaction.domain.TransactionType;
import com.top.wallet.transaction.domain.WalletTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static java.lang.Math.abs;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
public class WalletTransactionResponse {

    private Integer id;
    private Double amount;
    private Integer userId;

    private static final String ID_PATH = "/wallet_transaction_id";
    private static final String AMOUNT_PATH = "/amount";
    private static final String USER_ID_PATH = "/user_id";

    public static WalletTransactionResponse fromJsonNode(JsonNode response) {
        if (response == null) {
            throw new InternalServerErrorException(ErrorCode.NULL_VALUE);
        }
        return WalletTransactionResponse.builder()
                .id(response.at(ID_PATH).asInt())
                .amount(response.at(AMOUNT_PATH).asDouble())
                .userId(response.at(USER_ID_PATH).asInt())
                .build();
    }

    public WalletTransaction toDomain () {
        return WalletTransaction.builder()
                .transactionId(id)
                .amount(abs(amount))
                .userId(userId)
                .transactionType(TransactionType.getType(amount).toString())
                .build();
    }
}
