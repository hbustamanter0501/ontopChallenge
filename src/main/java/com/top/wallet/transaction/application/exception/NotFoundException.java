package com.top.wallet.transaction.application.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class NotFoundException extends RuntimeException {
    private String code;
    private HttpStatus status;

    public NotFoundException(HttpStatus status, String code, String message){
        super(message);
        this.status = status;
        this.code = code;
    }
}
