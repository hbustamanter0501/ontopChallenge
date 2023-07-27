package com.top.wallet.transaction.utils;

import org.springframework.http.HttpEntity;

public class RestUtils {
    public static HttpEntity<Object> buildHttpEntity(Object body) {

        return new HttpEntity<>(body);
    }

}
