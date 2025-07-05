package com.ms.learn.java.records.sb.exception;

import com.ms.learn.java.records.sb.infrastructure.exception.BaseException;

public class NotFoundException extends BaseException {
    public NotFoundException(String message) {
        super(message);
    }
}
