package com.top.wallet.transaction.application.exception;


import com.top.wallet.transaction.config.ErrorCode;

public abstract class ErrorCodeException extends RuntimeException {

    private final ErrorCode errorCode;

    public ErrorCodeException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}
