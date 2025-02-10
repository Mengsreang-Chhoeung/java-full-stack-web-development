package com.ms.spring_security_jwt.config;

import com.ms.spring_security_jwt.modules.security.exception.AuthenticationEntryPointException;
import com.ms.spring_security_jwt.modules.security.exception.AuthorizationEntryPointException;
import com.ms.spring_security_jwt.modules.security.filter.JwtFilter;
import com.ms.spring_security_jwt.modules.security.service.UserAuthDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final AuthenticationEntryPointException authenticationEntryPointException;
    private final AuthorizationEntryPointException authorizationEntryPointException;
    private final UserAuthDetailsService userAuthDetailsService;

    @Autowired
    public SecurityConfig(AuthenticationEntryPointException authenticationEntryPointException, AuthorizationEntryPointException authorizationEntryPointException, UserAuthDetailsService userAuthDetailsService) {
        this.authenticationEntryPointException = authenticationEntryPointException;
        this.authorizationEntryPointException = authorizationEntryPointException;
        this.userAuthDetailsService = userAuthDetailsService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // disable csrf filter
        http.csrf(AbstractHttpConfigurer::disable);
        // disable logout filter
        http.logout(AbstractHttpConfigurer::disable);
        // disable default login page
        http.formLogin(AbstractHttpConfigurer::disable);
        // disable request cache aware filter
        http.requestCache(AbstractHttpConfigurer::disable);
        // disable http basic
        http.httpBasic(AbstractHttpConfigurer::disable);
        // make this application te be stateless
        http.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(new JwtFilter(authenticationEntryPointException, userAuthDetailsService), BasicAuthenticationFilter.class);

        http.exceptionHandling(ex -> ex
                .authenticationEntryPoint(this.authenticationEntryPointException)
                .accessDeniedHandler(this.authorizationEntryPointException)
        );

        http.authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
        );

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
