package com.ms.spring_security_jwt;

import com.ms.spring_security_jwt.util.ApplicationContextUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class StaticInitializer {
    public StaticInitializer(ApplicationContext context) {
        // set context
        ApplicationContextUtil.setContext(context);
    }
}
