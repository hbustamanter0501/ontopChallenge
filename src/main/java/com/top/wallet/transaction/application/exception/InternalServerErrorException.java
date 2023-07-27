package com.top.wallet.transaction.application.exception;

import com.top.wallet.transaction.config.ErrorCode;

public class InternalServerErrorException extends ErrorCodeException {

    public InternalServerErrorException(ErrorCode errorCode) {
        super(errorCode);
    }
}
