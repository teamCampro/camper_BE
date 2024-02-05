package com.campro.member.domain;

import java.util.UUID;

public record Member(Long id, String email, String password, String nickname, String profileImage, Rule rule) {

    // S3 연동 후 수정 필요
    private static final String DEFAULT_PROFILE_IMAGE = "default.png";

    public static Member from(String email, String password) {
        return new Member(
                null,
                email,
                password,
                UUID.randomUUID().toString(),
                DEFAULT_PROFILE_IMAGE,
                Rule.USER
        );
    }
}
