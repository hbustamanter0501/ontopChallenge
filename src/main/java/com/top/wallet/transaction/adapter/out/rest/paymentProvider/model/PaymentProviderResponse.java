package com.top.wallet.transaction.adapter.out.rest.paymentProvider.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.top.wallet.transaction.application.exception.InternalServerErrorException;
import com.top.wallet.transaction.config.ErrorCode;
import com.top.wallet.transaction.domain.PaymentProvider;
import com.top.wallet.transaction.domain.TransactionType;
import com.top.wallet.transaction.domain.WalletTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
public class PaymentProviderResponse {

    private String status;
    private Double amount;
    private String providerTransactionId;


    private static final String STATUS_PATH = "/requestInfo/status";
    private static final String AMOUNT_PATH = "/paymentInfo/amount";
    private static final String PROVIDER_TRANSACTION_ID_PATH = "/paymentInfo/id";

    public static PaymentProviderResponse fromJsonNode(JsonNode response) {
        if (response == null) {
            throw new InternalServerErrorException(ErrorCode.NULL_VALUE);
        }
        return PaymentProviderResponse.builder()
                .status(response.at(STATUS_PATH).asText())
                .amount(response.at(AMOUNT_PATH).asDouble())
                .providerTransactionId(response.at(PROVIDER_TRANSACTION_ID_PATH).asText())
                .build();
    }

    public PaymentProvider toDomain () {
        return PaymentProvider.builder()
                .status(status)
                .amount(amount)
                .providerTransactionId(providerTransactionId)
                .build();
    }
}
