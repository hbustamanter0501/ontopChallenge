package com.top.wallet.transaction.config;

import com.top.wallet.transaction.application.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({InternalServerErrorException.class})
    public ResponseEntity handle(InternalServerErrorException ex) {
        String errorName = ex.getErrorCode().name();
        log.error(errorName, ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorName);
    }

    @ExceptionHandler({JsonParseErrorException.class})
    public ResponseEntity handle(JsonParseErrorException ex) {
        String errorName = ex.getErrorCode().name();
        log.error(errorName, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorName);
    }

    @ExceptionHandler({NoBalanceException.class})
    public ResponseEntity handle(NoBalanceException ex) {
        String errorName = ex.getErrorCode().name();
        log.error(errorName, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(errorName);
    }

    @ExceptionHandler({InvalidValueException.class})
    public ResponseEntity handle(InvalidValueException ex) {
        String errorName = ex.getErrorCode().name();
        log.error(errorName, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorName);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity handle(MethodArgumentTypeMismatchException ex) {
        log.error("Bad argument", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .build();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity handle(MethodArgumentNotValidException ex) {
        log.error("Bad argument", ex);
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(camelToSnake(fieldName), errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(errors);
    }

    private static String camelToSnake(String str) {
        return str.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }
}

