package com.ms.learn.java.records.sb.exception;

import com.ms.learn.java.records.sb.infrastructure.exception.BaseException;

public class BadRequestException extends BaseException {
    public BadRequestException(String message) {
        super(message);
    }
}
