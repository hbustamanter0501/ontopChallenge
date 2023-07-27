package com.top.wallet.transaction.application.exception;

import com.top.wallet.transaction.config.ErrorCode;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class NoBalanceException extends ErrorCodeException {

    public NoBalanceException(ErrorCode errorCode) {
        super(errorCode);
    }
}
