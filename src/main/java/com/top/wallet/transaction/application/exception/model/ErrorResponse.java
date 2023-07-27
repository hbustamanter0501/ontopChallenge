package com.top.wallet.transaction.application.exception.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.top.wallet.transaction.config.ErrorCode;
import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class ErrorResponse {
    @JsonIgnore
    int httpStatus;
    ErrorCode code;
    Map<String, String> fields;
}
