package com.ms.spring_security.config;

import com.ms.spring_security.constant.RestURIConstant;
import com.ms.spring_security.module.security.exception.AuthenticationEntryPointException;
import com.ms.spring_security.module.security.exception.AuthorizationEntryPointException;
import com.ms.spring_security.module.security.filter.CustomAuthFilter;
import com.ms.spring_security.module.security.service.UserAuthDetailsService;
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
    private final UserAuthDetailsService userAuthDetailsService;
    private final AuthorizationEntryPointException authorizationEntryPointException;

    @Autowired
    public SecurityConfig(AuthenticationEntryPointException authenticationEntryPointException, UserAuthDetailsService userAuthDetailsService, AuthorizationEntryPointException authorizationEntryPointException) {
        this.authenticationEntryPointException = authenticationEntryPointException;
        this.userAuthDetailsService = userAuthDetailsService;
        this.authorizationEntryPointException = authorizationEntryPointException;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .formLogin(Customizer.withDefaults());

//        http.addFilterBefore(new TenantFilter(this.authenticationEntryPointException), AuthorizationFilter.class);

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

        http.addFilterBefore(new CustomAuthFilter(authenticationEntryPointException, userAuthDetailsService), BasicAuthenticationFilter.class);

        http.exceptionHandling(ex -> ex
                .authenticationEntryPoint(this.authenticationEntryPointException)
                .accessDeniedHandler(this.authorizationEntryPointException)
        );

        http
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(RestURIConstant.CUSTOM_AUTH + "/register").permitAll()
                                .requestMatchers(RestURIConstant.CUSTOM_AUTH + "/login").permitAll()
                                .requestMatchers(RestURIConstant.CUSTOM_AUTH + "/a/**").permitAll()
                                .requestMatchers(RestURIConstant.CUSTOM_AUTH + "/admin").hasAnyAuthority("ADMIN", "USER", "SUPER_ADMIN")
                                .requestMatchers(RestURIConstant.CUSTOM_AUTH + "/deny-all").denyAll()
                                .requestMatchers(RestURIConstant.CUSTOM_AUTH + "/anonymous").anonymous()
                                .requestMatchers(RestURIConstant.CUSTOM_AUTH + "/authenticated").authenticated()
                                .anyRequest().authenticated()
                );

        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("kok")
//                .password("password")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("password")
//                .roles("USER", "ADMIN")
//                .build();
//
//        System.out.println("Hello user: " + user.getPassword());
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
