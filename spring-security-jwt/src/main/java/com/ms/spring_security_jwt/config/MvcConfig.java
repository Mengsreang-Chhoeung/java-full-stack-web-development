package com.ms.spring_security_jwt.config;

import com.ms.spring_security_jwt.infrastructure.resolver.UrlParamArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
    private final UrlParamArgumentResolver urlParamArgumentResolver;

    @Autowired
    public MvcConfig(UrlParamArgumentResolver urlParamArgumentResolver) {
        this.urlParamArgumentResolver = urlParamArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(this.urlParamArgumentResolver);
    }
}
