package com.top.wallet.transaction.adapter.out.rest

import com.top.wallet.transaction.adapter.out.rest.balance.BalanceRestAdapter
import com.top.wallet.transaction.adapter.out.rest.balance.model.BalanceResponse
import com.top.wallet.transaction.application.port.out.rest.BalanceRestAdapterPort
import com.top.wallet.transaction.domain.Balance
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class BalanceRestAdapterTest extends Specification {

    RestTemplate restTemplate = Mock()
    BalanceRestAdapterPort target = new BalanceRestAdapter(
            restTemplate,
            "http://mockoon.tools.getontop.com:3000/wallets/balance?user_id={userId}"
    )

    def "given a user id, it retrieve the balance : expected success "(){
        given:
        def userId = 1000
        def uri = "http://mockoon.tools.getontop.com:3000/wallets/balance?user_id=1000"

        def balance = BalanceResponse.builder()
                .userId(userId)
                .balance(2500)
                .build()

        def expected = Balance.builder()
                .userId(userId)
                .balance(2500)
                .build()

        when:
        def response = target.retrieveBalance(userId)

        then:
        1 * restTemplate.exchange(uri, HttpMethod.GET, null, BalanceResponse.class) >> ResponseEntity.of(Optional.of(balance))
        response == expected
    }


}