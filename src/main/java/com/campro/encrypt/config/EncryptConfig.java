package com.campro.encrypt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class EncryptConfig {

    private final EncryptProperties encryptProperties;

    public EncryptConfig(EncryptProperties encryptProperties) {
        this.encryptProperties = encryptProperties;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public BytesEncryptor asBytesEncryptor() {
        return new AesBytesEncryptor(encryptProperties.password(), encryptProperties.salt());
    }
}
