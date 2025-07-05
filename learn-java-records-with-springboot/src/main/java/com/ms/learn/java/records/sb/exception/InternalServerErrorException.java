package com.ms.learn.java.records.sb.exception;

import com.ms.learn.java.records.sb.infrastructure.exception.BaseException;

public class InternalServerErrorException extends BaseException {
    public InternalServerErrorException(String message) {
        super(message);
    }
}
