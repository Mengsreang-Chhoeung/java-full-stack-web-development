package com.ms.spring_security.exception;

import com.ms.spring_security.infrastructure.exception.BaseException;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
