package com.ms.spring_security_jwt.infrastructure.exception;

public class InternalServerErrorException extends BaseException {
    public InternalServerErrorException(String message) {
        super(message);
    }
}
