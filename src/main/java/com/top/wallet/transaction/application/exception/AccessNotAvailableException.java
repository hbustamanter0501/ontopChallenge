package com.top.wallet.transaction.application.exception;

import com.top.wallet.transaction.config.ErrorCode;

public class AccessNotAvailableException extends ErrorCodeException {

    public AccessNotAvailableException(ErrorCode errorCode) {
        super(errorCode);
    }
}
