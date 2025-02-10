package com.ms.spring_security_jwt.modules.security.exception;

import com.ms.spring_security_jwt.infrastructure.model.response.body.BaseBodyResponse;
import com.ms.spring_security_jwt.util.JsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthenticationEntryPointException implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(401);
        BaseBodyResponse failedBodyResponse = BaseBodyResponse.bodyFailed(httpStatusCode, authException.getMessage());

        response.setStatus(httpStatusCode.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        PrintWriter writer = response.getWriter();
        writer.print(JsonUtil.toJson(failedBodyResponse));
        writer.flush();
        writer.close();
    }
}
