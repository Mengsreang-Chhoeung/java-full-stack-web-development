package com.ms.spring_security_jwt.infrastructure.annotation.swagger;

import com.ms.spring_security_jwt.constant.AppConstant;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SecurityScheme(name = AppConstant.JWT_SCHEME_NAME, scheme = AppConstant.JWT_SCHEME, type = SecuritySchemeType.HTTP, description = "Spring Security With JWT", in = SecuritySchemeIn.HEADER)
public @interface ApiBearerAuthConfig {
}
