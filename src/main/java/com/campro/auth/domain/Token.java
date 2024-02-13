package com.campro.auth.domain;

public record Token(Long id, Long memberId, String data) {

    public static Token from(Long memberId, String data) {
        return new Token(
                null,
                memberId,
                data
        );
    }
}
