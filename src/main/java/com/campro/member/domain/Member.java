package com.campro.member.domain;

public record Member(Long id, String nickname, String email, String password, String profileImage, Rule rule) {

    // S3 연동 후 수정 필요
    private static final String DEFAULT_PROFILE_IMAGE = "default.png";

    public static Member from(String nickname, String email, String password) {
        return new Member(
                null,
                nickname,
                email,
                password,
                DEFAULT_PROFILE_IMAGE,
                Rule.USER
        );
    }
}
