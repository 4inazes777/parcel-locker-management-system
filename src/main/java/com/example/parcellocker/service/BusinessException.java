package com.example.parcellocker.service;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}