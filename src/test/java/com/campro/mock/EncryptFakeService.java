package com.campro.mock;

import com.campro.encrypt.application.service.EncryptService;

public class EncryptFakeService implements EncryptService {
    @Override
    public String recoverableEncryptData(String data) {
        return null;
    }

    @Override
    public String unrecoverableEncryptData(String data) {
        return null;
    }
}
