package com.ms.spring_file_upload.controller;

import com.ms.spring_file_upload.exception.BadRequestException;
import com.ms.spring_file_upload.exception.ConflictException;
import com.ms.spring_file_upload.exception.InternalServerErrorException;
import com.ms.spring_file_upload.exception.NotFoundException;
import com.ms.spring_file_upload.infrastructure.model.body.BaseBodyResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionController extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        return BaseBodyResponse.internalFailed(ex.getMessage(), statusCode);
    }

    @ExceptionHandler(value = {Exception.class, InternalServerErrorException.class})
    public ResponseEntity<BaseBodyResponse> handleException(Exception ex) {
        return BaseBodyResponse.failed(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<BaseBodyResponse> handleBadRequestException(BadRequestException ex) {
        return BaseBodyResponse.failed(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<BaseBodyResponse> handleNotFoundException(NotFoundException ex) {
        return BaseBodyResponse.failed(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ConflictException.class)
    public ResponseEntity<BaseBodyResponse> handleConflictException(ConflictException ex) {
        return BaseBodyResponse.failed(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
