package com.ms.spring_security_jwt.infrastructure.aspect;

import com.ms.spring_security_jwt.infrastructure.exception.AuthException;
import com.ms.spring_security_jwt.util.ApplicationUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class RequireAuthenticatedUserAspect {
    // Pointcut for method level
    @Pointcut("@annotation(com.ms.spring_security_jwt.infrastructure.annotation.auth.RequireAuthenticatedUser)")
    public void isMethodLevel() {}

    // Pointcut for class level
    @Pointcut("@within(com.ms.spring_security_jwt.infrastructure.annotation.auth.RequireAuthenticatedUser)")
    public void isClassLevel() {}

    @Before("isMethodLevel() || isClassLevel()")
    public void checkAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!ApplicationUtil.isAuthenticated(authentication)) {
            throw new AuthException("Full authentication is required to access this resource");
        }
    }
}
