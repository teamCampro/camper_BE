package com.campro.member.presentation.controller.response;

public record MemberSignupResponse(String email, String accessToken, String refreshToken) {

    public static MemberSignupResponse from(String email, String accessToken, String refreshToken) {
        return new MemberSignupResponse(email, accessToken, refreshToken);
    }
}
