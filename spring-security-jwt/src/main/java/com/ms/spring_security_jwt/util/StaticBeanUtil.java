package com.ms.spring_security_jwt.util;

import com.ms.spring_security_jwt.infrastructure.property.AppProperty;
import com.ms.spring_security_jwt.modules.security.model.UserAuthDetails;
import com.ms.spring_security_jwt.modules.security.service.UserAuthDetailsService;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public final class StaticBeanUtil {
    private static final ApplicationContext context = ApplicationContextUtil.getContext();

    public static AppProperty getAppProperty() {
        return context.getBean(AppProperty.class);
    }

    public static PasswordEncoder getPasswordEncoder() {
        return context.getBean(PasswordEncoder.class);
    }

    public static Optional<UserAuthDetails> getCurrentUserOrNull() {
        return context.getBean(UserAuthDetailsService.class).getCurrentUserOrNull();
    }
}
