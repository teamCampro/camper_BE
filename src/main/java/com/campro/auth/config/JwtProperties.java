package com.campro.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(
        String issuer,
        String accessSecretKey,
        Long accessExpiredAt,
        String refreshSecretKey,
        Long refreshExpiredAt
) {
}
