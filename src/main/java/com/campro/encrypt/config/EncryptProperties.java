package com.campro.encrypt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "encryptor")
public record EncryptProperties(String password, String salt) {
}
