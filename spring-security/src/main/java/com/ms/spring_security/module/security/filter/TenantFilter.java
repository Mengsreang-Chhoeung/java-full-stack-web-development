package com.ms.spring_security.module.security.filter;

import com.ms.spring_security.exception.TenantException;
import com.ms.spring_security.module.security.exception.AuthenticationEntryPointException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

public class TenantFilter extends OncePerRequestFilter {
    private final AuthenticationEntryPointException authenticationEntryPointException;

    public TenantFilter(AuthenticationEntryPointException authenticationEntryPointException) {
        this.authenticationEntryPointException = authenticationEntryPointException;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tenantId = request.getHeader("x-tenant-id");
        if (Objects.equals(tenantId, "ms")) {
            filterChain.doFilter(request, response);
        } else {
            this.authenticationEntryPointException.commence(request, response, new TenantException("Invalid Tenant ID!"));
        }
    }
}
