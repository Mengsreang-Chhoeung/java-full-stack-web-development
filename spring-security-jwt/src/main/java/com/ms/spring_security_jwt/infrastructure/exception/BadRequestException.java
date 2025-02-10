package com.ms.spring_security_jwt.infrastructure.exception;

public class BadRequestException extends BaseException {
    public BadRequestException(String msg) {
        super(msg);
    }
}
