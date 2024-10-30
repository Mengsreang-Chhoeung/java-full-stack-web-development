package com.ms.spring_security.exception;

import com.ms.spring_security.infrastructure.exception.BaseException;

public class ConflictException extends BaseException {
    public ConflictException(String message) {
        super(message);
    }
}
