package com.campro.common.controller;

public enum ResponseCode {

    SIGNUP_SUCCESS("S001", "회원 가입 성공"),
    LOGIN_SUCCESS("S002", "로그인 성공"),
    GET_INFO_SUCCESS("S003", "조회 성공"),

    GET_ONBOARDINGLIST_SUCCESS("O001","온보딩 리스트 조회");

    private final String code;
    private final String message;

    ResponseCode(String code, String message) {
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