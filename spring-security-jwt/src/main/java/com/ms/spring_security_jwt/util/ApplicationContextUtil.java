package com.ms.spring_security_jwt.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextException;

public final class ApplicationContextUtil {
    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        if (context == null) throw new ApplicationContextException("Application context load failed!");

        return context;
    }

    public static void setContext(ApplicationContext _context) {
        context = _context;
    }
}
