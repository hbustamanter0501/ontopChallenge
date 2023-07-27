package com.top.wallet.transaction.adapter.out.rest.paymentProvider;

import com.fasterxml.jackson.databind.JsonNode;
import com.top.wallet.transaction.adapter.in.controller.model.AccountDTO;
import com.top.wallet.transaction.adapter.in.controller.model.WalletTransactionDTO;
import com.top.wallet.transaction.adapter.out.rest.handler.RestTemplateErrorHandler;
import com.top.wallet.transaction.adapter.out.rest.paymentProvider.model.AccountRequest;
import com.top.wallet.transaction.adapter.out.rest.paymentProvider.model.PaymentProviderResponse;
import com.top.wallet.transaction.application.exception.AccessNotAvailableException;
import com.top.wallet.transaction.application.exception.InvalidValueException;
import com.top.wallet.transaction.application.exception.NotFoundException;
import com.top.wallet.transaction.application.port.out.persistence.AccountPersistencePort;
import com.top.wallet.transaction.application.port.out.rest.PaymentProviderRestAdapterPort;
import com.top.wallet.transaction.config.ErrorCode;
import com.top.wallet.transaction.domain.Currency;
import com.top.wallet.transaction.domain.EntityType;
import com.top.wallet.transaction.domain.PaymentProvider;
import com.top.wallet.transaction.utils.JsonUtils;
import com.top.wallet.transaction.utils.RestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.Math.abs;

@Slf4j
@Component
public class PaymentProviderRestAdapter implements PaymentProviderRestAdapterPort {

    private final RestTemplate restTemplate;
    private final AccountPersistencePort accountPersistence;
    private final String createPaymentInProviderUrl;
    private final String ontopName;
    private final String ontopAccountNumber;
    private final String ontopRoutingNumber;
    private final Double ontopTransactionFee;

    public PaymentProviderRestAdapter (
            RestTemplate restTemplate,
            AccountPersistencePort accountPersistence,
            @Value("${create.payment.provider.url}") String createPaymentInProviderUrl,
            @Value("${ontop.name}") String ontopName,
            @Value("${ontop.account.number}") String ontopAccountNumber,
            @Value("${ontop.routing.number}") String ontopRoutingNumber,
            @Value("${ontop.transaction.fee}") Double ontopTransactionFee) {
        this.restTemplate = restTemplate;
        this.createPaymentInProviderUrl = createPaymentInProviderUrl;
        this.ontopName = ontopName;
        this.ontopAccountNumber = ontopAccountNumber;
        this.ontopRoutingNumber = ontopRoutingNumber;
        this.accountPersistence = accountPersistence;
        this.ontopTransactionFee = ontopTransactionFee;

        var errorHandler = new RestTemplateErrorHandler(
                Map.of(HttpStatus.BAD_REQUEST, new InvalidValueException(ErrorCode.NULL_VALUE),
                        HttpStatus.INTERNAL_SERVER_ERROR, new AccessNotAvailableException(ErrorCode.ACCESS_NOT_AVAILABLE)
                ));

        this.restTemplate.setErrorHandler(errorHandler);
    }

    @Override
    public PaymentProvider createPaymentInProvider (WalletTransactionDTO dto) {
        HttpMethod method = HttpMethod.POST;
        Map<String, Object> request = new HashMap<>();

        request.put("source", getSourceInformation());
        request.put("destination", getDestinationInformation(dto.getUserId()));
        request.put("amount", getWithdrawAmount(dto.getAmount()));

        log.debug("request: {}", JsonUtils.writeValueAsString(request));

        ResponseEntity<JsonNode> paymentProviderResponse = restTemplate.exchange(createPaymentInProviderUrl, method, RestUtils.buildHttpEntity(request), JsonNode.class);
        log.debug("Payment provider response: {}", paymentProviderResponse.getBody());
        PaymentProviderResponse response = PaymentProviderResponse.fromJsonNode(paymentProviderResponse.getBody());

        return response.toDomain();
    }

    private Map<String, Object> getSourceInformation () {
        Map<String, Object> source = new HashMap<>();
        Map<String, Object> sourceInformation = new HashMap<>();
        sourceInformation.put("name", ontopName);
        AccountRequest account = AccountRequest.builder()
                .accountNumber(ontopAccountNumber)
                .currency(Currency.USD.toString())
                .routingNumber(ontopRoutingNumber)
                .build();
        source.put("type", EntityType.COMPANY.toString());
        source.put("sourceInformation", sourceInformation);
        source.put("account", account);

        return source;
    }

    private Map<String, Object> getDestinationInformation (Integer userId) {
        Map<String, Object> destination = new HashMap<>();
        AccountDTO account = this.accountPersistence.findByUserId(userId);

        if(Objects.isNull(account)){
            throw new NotFoundException(HttpStatus.NOT_FOUND, String.valueOf(HttpStatus.NOT_FOUND.value()),
                    String.format("Error, User not found in DDBB"));
        }

        destination.put("name", account.getFullName());

        Map<String, Object> accountInformation = new HashMap<>();
        accountInformation.put("accountNumber", account.getAccountNumber());
        accountInformation.put("currency", Currency.USD.toString());
        accountInformation.put("routingNumber", account.getRoutingNumber());

        destination.put("account", accountInformation);



        return destination;
    }

    private Double getWithdrawAmount (Double amount){
        return abs(amount)*(1-ontopTransactionFee);
    }
}
