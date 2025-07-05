package com.ms.learn.java.records.sb.modules.app.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ms.learn.java.records.sb.exception.*;
import com.ms.learn.java.records.sb.infrastructure.model.response.body.BaseBodyResponse;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
            HttpStatusCode statusCode, WebRequest request) {
        return BaseBodyResponse.internalFailed(statusCode, ex.getMessage());
    }

    @ExceptionHandler({ Exception.class, InternalServerErrorException.class })
    public ResponseEntity<BaseBodyResponse> handleInternalServerErrorException(Exception ex) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        return BaseBodyResponse.failed(httpStatus, ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BaseBodyResponse> handleBadRequestException(BadRequestException ex) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        return BaseBodyResponse.failed(httpStatus, ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BaseBodyResponse> handleNotFoundException(NotFoundException ex) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        return BaseBodyResponse.failed(httpStatus, ex.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<BaseBodyResponse> handleConflictException(ConflictException ex) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;

        return BaseBodyResponse.failed(httpStatus, ex.getMessage());
    }

}
