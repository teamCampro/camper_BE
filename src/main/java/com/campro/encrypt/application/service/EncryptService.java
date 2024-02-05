package com.campro.encrypt.application.service;

public interface EncryptService {
    String recoverableEncryptData(String data);
    String unrecoverableEncryptData(String data);
}
