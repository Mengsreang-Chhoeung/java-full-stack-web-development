package com.ms.spring_security_jwt.infrastructure.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
@Getter
public class AppProperty {
    @Value("${spring.application.name}")
    private String appName;
    @Value("${spring.application.description}")
    private String appDescription;
    @Value("${spring.application.version}")
    private String appVersion;
    @Value("${spring.application.origin-url}")
    private String appOriginUrl;
}
