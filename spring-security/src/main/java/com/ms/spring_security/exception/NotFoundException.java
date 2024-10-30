package com.ms.spring_security.exception;

import com.ms.spring_security.infrastructure.exception.BaseException;

public class NotFoundException extends BaseException {
    public NotFoundException(String message) {
        super(message);
    }
}
