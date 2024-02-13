package com.campro.common.exception;

public enum ErrorCode {

    SIGNUP_FAIL_BY_DUPLICATE_EMAIL("E001", "이미 사용중인 이메일 입니다."),
    SIGNUP_FAIL_BY_DUPLICATE_NICKNAME("E002", "이미 사용중인 닉네임 입니다."),
    NON_EXPECTED_EXCEPTION("E003", "예상하지 못한 예외 발생"),
    METHOD_ARGUMENT_NOT_VALID("E004", "요청에 필요한 값이 잘못되었습니다."),
    LOGIN_FAIL_BY_NOT_EXIST_EMAIL("E005", "존재하지 않는 이메일 입니다."),
    MISSING_REQUEST_HEADER("E006", "요청에 필요한 필수 값이 누락되었습니다."),
    NON_EXPECTED_SERVLET_EXCEPTION("E007", "예상하지 못한 HTTP 요청 예외 발생"),
    LOGIN_FAIL_BY_PASSWORD_MISMATCH("E008", "패스워드가 일치하지 않습니다.");



    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
