package com.top.wallet.transaction.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.top.wallet.transaction.application.exception.JsonParseErrorException;
import com.top.wallet.transaction.config.ErrorCode;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String writeValueAsString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new JsonParseErrorException(ErrorCode.JSON_PARSE_ERROR);
        }
    }
}
