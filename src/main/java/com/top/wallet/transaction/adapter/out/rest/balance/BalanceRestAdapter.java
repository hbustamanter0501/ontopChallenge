package com.top.wallet.transaction.adapter.out.rest.balance;

import com.top.wallet.transaction.adapter.out.rest.balance.model.BalanceResponse;
import com.top.wallet.transaction.application.port.out.persistence.BalancePersistencePort;
import com.top.wallet.transaction.application.port.out.rest.BalanceRestAdapterPort;
import com.top.wallet.transaction.domain.Balance;
import com.top.wallet.transaction.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
public class BalanceRestAdapter implements BalanceRestAdapterPort {

    private final RestTemplate restTemplate;
    private final String retrieveBalanceUrl;

    public BalanceRestAdapter (
            RestTemplate restTemplate,
            @Value("${retrieve.wallet.balance.url}") String retrieveBalanceUrl){
        this.restTemplate = restTemplate;
        this.retrieveBalanceUrl = retrieveBalanceUrl;
    }

    @Override
    public Balance retrieveBalance (Integer userId) {
        String uri = UriComponentsBuilder.fromHttpUrl(retrieveBalanceUrl).buildAndExpand(userId).toUriString();
        BalanceResponse response = restTemplate.exchange(uri, HttpMethod.GET, null, BalanceResponse.class).getBody();
        log.debug("Balance response: {}", JsonUtils.writeValueAsString(response));
        return response.toDomain();
    }

}
