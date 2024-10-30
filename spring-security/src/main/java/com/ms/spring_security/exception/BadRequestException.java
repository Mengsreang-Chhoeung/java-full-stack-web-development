package com.ms.spring_security.exception;

import com.ms.spring_security.infrastructure.exception.BaseException;

public class BadRequestException extends BaseException {
    public BadRequestException(String message) {
        super(message);
    }
}
