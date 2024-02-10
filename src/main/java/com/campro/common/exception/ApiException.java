package com.campro.common.exception;

public class ApiException extends RuntimeException {

    private final String code;
    private final String message;

    public ApiException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiException(Throwable cause, String code, String message) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }
}
