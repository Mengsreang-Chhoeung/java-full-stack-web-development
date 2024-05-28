package com.ms.spring_file_upload.exception;

import com.ms.spring_file_upload.infrastructure.exception.BaseException;

public class BadRequestException extends BaseException {
    public BadRequestException(String message) {
        super(message);
    }
}
