package com.ms.spring_security_jwt.infrastructure.exception;

public class ConflictException extends BaseException {
    public ConflictException(String msg) {
        super(msg);
    }
}
