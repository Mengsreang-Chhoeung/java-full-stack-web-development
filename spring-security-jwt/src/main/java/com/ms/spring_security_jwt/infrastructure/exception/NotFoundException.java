package com.ms.spring_security_jwt.infrastructure.exception;

public class NotFoundException extends BaseException {
    public NotFoundException(String message) {
        super(message);
    }
}
