package com.campro.member.presentation.controller.response;

public record MemberLoginResponse(String email, String accessToken, String refreshToken) {

    public static MemberLoginResponse from(String email, String accessToken, String refreshToken) {
        return new MemberLoginResponse(email, accessToken, refreshToken);
    }
}
