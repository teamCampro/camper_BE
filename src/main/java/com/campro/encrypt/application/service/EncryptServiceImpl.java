package com.campro.encrypt.application.service;

import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Base64;

@Service
public class EncryptServiceImpl implements EncryptService {

    private final PasswordEncoder passwordEncoder;
    private final BytesEncryptor bytesEncryptor;

    public EncryptServiceImpl(PasswordEncoder passwordEncoder, BytesEncryptor bytesEncryptor) {
        this.passwordEncoder = passwordEncoder;
        this.bytesEncryptor = bytesEncryptor;
    }

    @Override
    public String recoverableEncryptData(String data) {
        Assert.notNull(data, "암호화 데이터가 존재하지 않습니다.");
        return Base64.getEncoder().encodeToString(bytesEncryptor.encrypt(data.getBytes()));
    }

    @Override
    public String unrecoverableEncryptData(String data) {
        Assert.notNull(data, "암호화 데이터가 존재하지 않습니다.");
        return passwordEncoder.encode(data);
    }

    public boolean matchData(String rawData, String data) {
        Assert.notNull(data, "비교할 데이터가 존재하지 않습니다.");
        return passwordEncoder.matches(rawData, data);
    }
}
