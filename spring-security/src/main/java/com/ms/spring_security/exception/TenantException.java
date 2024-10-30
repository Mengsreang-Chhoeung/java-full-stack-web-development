package com.ms.spring_security.exception;

import org.springframework.security.core.AuthenticationException;

public class TenantException extends AuthenticationException {
    public TenantException(String msg) {
        super(msg);
    }
}
