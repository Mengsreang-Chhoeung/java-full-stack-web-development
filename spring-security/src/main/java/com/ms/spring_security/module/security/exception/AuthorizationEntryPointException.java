package com.ms.spring_security.module.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.spring_security.infrastructure.model.response.body.BaseBodyResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthorizationEntryPointException implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(403);
        BaseBodyResponse failedBodyResponse = BaseBodyResponse.bodyFailed(httpStatusCode, accessDeniedException.getMessage());

        response.setStatus(httpStatusCode.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        PrintWriter writer = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        writer.print(objectMapper.writeValueAsString(failedBodyResponse));
        writer.flush();
        writer.close();
    }
}
