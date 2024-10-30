package com.ms.spring_security.exception;

import org.springframework.security.core.AuthenticationException;

public class CustomAuthException extends AuthenticationException {
    public CustomAuthException(String msg) {
        super(msg);
    }
}
