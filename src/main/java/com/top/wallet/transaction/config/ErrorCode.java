package com.top.wallet.transaction.config;

public enum ErrorCode {
    JSON_PARSE_ERROR(Constants.JSON_PARSE_ERROR),
    NULL_VALUE(Constants.NULL_VALUE),
    AMOUNT_VALUE_NOT_VALID(Constants.AMOUNT_VALUE_NOT_VALID),
    NO_BALANCE_FOR_TRANSACTION(Constants.NO_BALANCE_FOR_TRANSACTION),
    USER_NOT_FOUND(Constants.USER_NOT_FOUND),
    ACCESS_NOT_AVAILABLE(Constants.ACCESS_NOT_AVAILABLE);

    ErrorCode(String value){
    };

    public static class Constants {
        public static final String JSON_PARSE_ERROR = "JSON_PARSE_ERROR";
        public static final String NULL_VALUE = "NULL_VALUE";
        public static final String AMOUNT_VALUE_NOT_VALID = "AMOUNT_VALUE_NOT_VALID";
        public static final String NO_BALANCE_FOR_TRANSACTION = "NO_BALANCE_FOR_TRANSACTION";
        public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
        public static final String ACCESS_NOT_AVAILABLE = "ACCESS_NOT_AVAILABLE";
    }
}
