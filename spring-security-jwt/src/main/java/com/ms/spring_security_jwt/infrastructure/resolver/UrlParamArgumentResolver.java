package com.ms.spring_security_jwt.infrastructure.resolver;

import com.ms.spring_security_jwt.infrastructure.service.UrlParamService;
import com.ms.spring_security_jwt.infrastructure.service.implementation.UrlParamServiceImpl;
import jakarta.annotation.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class UrlParamArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return UrlParamService.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer, NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) {
        String _isPaged = webRequest.getParameter("isPaged") == null ? "true" : webRequest.getParameter("isPaged");
        String _sort = webRequest.getParameter("sort") == null ? "id:desc" : webRequest.getParameter("sort");

        return new UrlParamServiceImpl(webRequest.getParameter("q"), webRequest.getParameter("page"), webRequest.getParameter("size"), _isPaged, _sort);
    }
}
