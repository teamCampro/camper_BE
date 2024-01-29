package com.campro.common.controller.exception;

public enum ErrorCode {

    NON_AVAILABLE_SIGNUP_MEMBER("E001", "회원가입이 불가능한 멤버입니다."),
    NOT_FOUND_AVAILABLE_MEMBER_BY_NAME("E002", "존재하지 않는 회원 이름입니다."),
    NON_EXPECTED_EXCEPTION("E003", "예상하지 못한 예외 발생"),

    LOGIN_FAILED("E005", "로그인 실패"),
    MISSING_REQUEST_HEADER("E006", "요청에 필요한 필수 값이 누락되었습니다."),
    NON_EXPECTED_SERVLET_EXCEPTION("E007", "예상하지 못한 HTTP 요청 예외 발생"),
    JWT_EXCEPTION("E008", "토큰 인증 오류");

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