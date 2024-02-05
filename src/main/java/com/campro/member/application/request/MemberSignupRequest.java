package com.campro.member.application.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record MemberSignupRequest(
        @Email(message = "email is not valid")
        @NotBlank(message = "email is required")
        String email,

        @NotBlank(message = "nickname is required")
        String nickname,

        @NotBlank(message = "password is required")
        String password
) {
}
