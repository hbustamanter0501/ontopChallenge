package com.top.wallet.transaction.application.exception;

import com.top.wallet.transaction.config.ErrorCode;

public class UserNotFoundException extends ErrorCodeException {

    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
