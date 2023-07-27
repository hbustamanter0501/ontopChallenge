package com.top.wallet.transaction.application.exception;

import com.top.wallet.transaction.config.ErrorCode;

public class JsonParseErrorException extends ErrorCodeException {

    public JsonParseErrorException(ErrorCode errorCode) {
        super(errorCode);
    }
}
