package com.ms.spring_security.module.security.filter;

import com.ms.spring_security.exception.CustomAuthException;
import com.ms.spring_security.module.security.exception.AuthenticationEntryPointException;
import com.ms.spring_security.module.security.service.UserAuthDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class CustomAuthFilter extends OncePerRequestFilter {
    private final AuthenticationEntryPointException authenticationEntryPointException;
    private final UserAuthDetailsService userAuthDetailsService;

    public CustomAuthFilter(AuthenticationEntryPointException authenticationEntryPointException, UserAuthDetailsService userAuthDetailsService) {
        this.authenticationEntryPointException = authenticationEntryPointException;
        this.userAuthDetailsService = userAuthDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String xAuthentication = request.getHeader("x-authorization");
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = this.userAuthDetailsService.getAuthentication(xAuthentication);

            if (usernamePasswordAuthenticationToken != null)
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            this.authenticationEntryPointException.commence(request, response, new CustomAuthException("Invalid Auth!"));
        }
    }
}
