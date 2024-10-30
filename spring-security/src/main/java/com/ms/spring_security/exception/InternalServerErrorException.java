package com.ms.spring_security.exception;

import com.ms.spring_security.infrastructure.exception.BaseException;

public class InternalServerErrorException extends BaseException {
    public InternalServerErrorException(String message) {
        super(message);
    }
}
