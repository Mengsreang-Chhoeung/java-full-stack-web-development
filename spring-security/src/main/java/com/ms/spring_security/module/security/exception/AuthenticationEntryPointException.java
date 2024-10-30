package com.ms.spring_security.module.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.spring_security.infrastructure.model.response.body.BaseBodyResponse;
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
        ObjectMapper objectMapper = new ObjectMapper();
        writer.print(objectMapper.writeValueAsString(failedBodyResponse));
        writer.flush();
        writer.close();
    }
}
