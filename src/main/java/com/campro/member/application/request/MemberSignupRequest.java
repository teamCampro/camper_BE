package com.campro.member.application.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record MemberSignupRequest(
        @NotBlank(message = "닉네임은 필수 입력값입니다.")
        String nickname,
        @Email(message = "이메일 형식에 맞지않는 입력값입니다.", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
        String email,
        @NotBlank(message = "비밀번호는 필수 입력값입니다.")
        String password
) {
}
