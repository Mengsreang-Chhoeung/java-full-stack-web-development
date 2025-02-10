package com.ms.spring_security_jwt.modules.security.filter;

import com.ms.spring_security_jwt.infrastructure.exception.AuthException;
import com.ms.spring_security_jwt.modules.security.exception.AuthenticationEntryPointException;
import com.ms.spring_security_jwt.modules.security.service.UserAuthDetailsService;
import com.ms.spring_security_jwt.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    private final AuthenticationEntryPointException authenticationEntryPointException;
    private final UserAuthDetailsService userAuthDetailsService;

    public JwtFilter(AuthenticationEntryPointException authenticationEntryPointException, UserAuthDetailsService userAuthDetailsService) {
        this.authenticationEntryPointException = authenticationEntryPointException;
        this.userAuthDetailsService = userAuthDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = JwtUtil.extractToken(request);
            if (token != null) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = this.userAuthDetailsService.getAuthentication(token);

                if (usernamePasswordAuthenticationToken != null)
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            System.out.println("Hello auth: " + ex.getMessage());
            this.authenticationEntryPointException.commence(request, response, new AuthException("Invalid Auth!"));
        }
    }
}
