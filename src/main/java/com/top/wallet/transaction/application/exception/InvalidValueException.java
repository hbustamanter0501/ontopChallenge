package com.top.wallet.transaction.application.exception;

import com.top.wallet.transaction.config.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidValueException extends ErrorCodeException {

    public InvalidValueException(ErrorCode errorCode) {
        super(errorCode);
    }
}
