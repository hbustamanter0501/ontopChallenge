package com.top.wallet.transaction.adapter.out.rest.walletTransaction;

import com.fasterxml.jackson.databind.JsonNode;
import com.top.wallet.transaction.adapter.in.controller.model.WalletTransactionDTO;
import com.top.wallet.transaction.adapter.out.rest.handler.RestTemplateErrorHandler;
import com.top.wallet.transaction.adapter.out.rest.walletTransaction.model.WalletTransactionRequest;
import com.top.wallet.transaction.adapter.out.rest.walletTransaction.model.WalletTransactionResponse;
import com.top.wallet.transaction.application.exception.AccessNotAvailableException;
import com.top.wallet.transaction.application.exception.InvalidValueException;
import com.top.wallet.transaction.application.exception.UserNotFoundException;
import com.top.wallet.transaction.application.port.out.rest.WalletTransactionRestAdapterPort;
import com.top.wallet.transaction.config.ErrorCode;
import com.top.wallet.transaction.domain.WalletTransaction;
import com.top.wallet.transaction.utils.RestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Component
public class WalletTransactionRestAdapter implements WalletTransactionRestAdapterPort {

    private final RestTemplate restTemplate;
    private final String createWalletTransactionUrl;

    public WalletTransactionRestAdapter (
            RestTemplate restTemplate,
            @Value("${create.wallet.transaction.url}") String createWalletTransactionUrl) {
        this.restTemplate = restTemplate;
        this.createWalletTransactionUrl = createWalletTransactionUrl;

        var errorHandler = new RestTemplateErrorHandler(
                Map.of(HttpStatus.BAD_REQUEST, new InvalidValueException(ErrorCode.NULL_VALUE),
                        HttpStatus.NOT_FOUND, new UserNotFoundException(ErrorCode.USER_NOT_FOUND),
                        HttpStatus.INTERNAL_SERVER_ERROR, new AccessNotAvailableException(ErrorCode.ACCESS_NOT_AVAILABLE)
                ));

        this.restTemplate.setErrorHandler(errorHandler);
    }

    @Override
    public WalletTransaction createTransaction (WalletTransactionDTO dto){
        HttpMethod method = HttpMethod.POST;

        WalletTransactionRequest request = WalletTransactionRequest.builder()
                .amount(dto.getAmount())
                .userId(dto.getUserId())
                .build();

        ResponseEntity<JsonNode> walletTransactionResponse = restTemplate.exchange(createWalletTransactionUrl, method, RestUtils.buildHttpEntity(request), JsonNode.class);
        log.debug("Wallet Transaction response: {}", walletTransactionResponse.getBody());
        WalletTransactionResponse response = WalletTransactionResponse.fromJsonNode(walletTransactionResponse.getBody());

        return response.toDomain();

    }


}
