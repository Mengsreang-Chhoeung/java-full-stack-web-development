package com.ms.spring_security_jwt.infrastructure.annotation.swagger;

import com.ms.spring_security_jwt.constant.AppConstant;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@SecurityRequirement(name = AppConstant.JWT_SCHEME_NAME)
public @interface ApiBearerAuth {
}
