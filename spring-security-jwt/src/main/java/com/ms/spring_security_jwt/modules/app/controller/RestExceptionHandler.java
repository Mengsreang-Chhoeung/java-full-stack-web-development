package com.ms.spring_security_jwt.modules.app.controller;

import com.ms.spring_security_jwt.infrastructure.exception.*;
import com.ms.spring_security_jwt.infrastructure.model.response.body.BaseBodyResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        return BaseBodyResponse.internalFailed(statusCode, ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String message = "Invalid value";

        for (ObjectError err : ex.getBindingResult().getAllErrors()) {
            message = err.getDefaultMessage();
        }

        return BaseBodyResponse.internalFailed(httpStatus, message);
    }

    @ExceptionHandler({Exception.class, InternalServerErrorException.class})
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

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<BaseBodyResponse> handleUnauthorizedException(UnauthorizedException ex) {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

        return BaseBodyResponse.failed(httpStatus, ex.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<BaseBodyResponse> handleForbiddenException(ForbiddenException ex) {
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;

        return BaseBodyResponse.failed(httpStatus, ex.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<BaseBodyResponse> handleAuthenticationException(AuthenticationException ex) {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

        return BaseBodyResponse.failed(httpStatus, ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<BaseBodyResponse> handleAccessDeniedException(AccessDeniedException ex) {
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;

        return BaseBodyResponse.failed(httpStatus, ex.getMessage());
    }
}
