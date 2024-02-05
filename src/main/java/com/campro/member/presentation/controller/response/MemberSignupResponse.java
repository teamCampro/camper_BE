package com.campro.member.presentation.controller.response;

public record MemberSignupResponse(String nickname) {

    public static MemberSignupResponse from(String nickname) {
        return new MemberSignupResponse(nickname);
    }
}
