package com.ms.spring_security_jwt.infrastructure.exception;

import org.springframework.security.access.AccessDeniedException;

public class ForbiddenException extends AccessDeniedException {
    public ForbiddenException(String message) {
        super(message);
    }
}
