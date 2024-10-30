package com.ms.spring_security.model.request.custom_auth;

import com.ms.spring_security.infrastructure.model.request.BaseRequest;
import com.ms.spring_security.model.entity.CustomAuthEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class RegisterCustomAuthRequest implements BaseRequest<CustomAuthEntity> {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public CustomAuthEntity toEntity() {
        return null;
    }

    public CustomAuthEntity toEntity(PasswordEncoder passwordEncoder) {
        CustomAuthEntity authEntity = new CustomAuthEntity();
        authEntity.setId(UUID.randomUUID());
        authEntity.setUsername(this.getUsername());
        authEntity.setPassword(passwordEncoder.encode(this.getPassword()));
        List<String> authorities = new ArrayList<>();
        authorities.add("USER");
        authorities.add("SUPPLIER");
        authorities.add("CUSTOMER");
        authEntity.setAuthorities(authorities);
        authEntity.setAccountNonExpired(true);
        authEntity.setCredentialsNonExpired(true);
        authEntity.setAccountNonLocked(true);
        authEntity.setEnabled(true);

        return authEntity;
    }
}
