package com.ms.spring_security.exception;

import com.ms.spring_security.infrastructure.exception.BaseException;

public class ForbiddenException extends BaseException {
    public ForbiddenException(String message) {
        super(message);
    }
}
