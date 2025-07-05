package com.ms.learn.java.records.sb.exception;

import com.ms.learn.java.records.sb.infrastructure.exception.BaseException;

public class ConflictException extends BaseException {
    public ConflictException(String message) {
        super(message);
    }
}
