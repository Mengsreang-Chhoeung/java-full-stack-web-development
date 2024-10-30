package com.ms.spring_security.model.request.custom_auth;

import com.ms.spring_security.infrastructure.model.request.BaseRequest;
import com.ms.spring_security.model.entity.CustomAuthEntity;

public class LoginCustomAuthRequest implements BaseRequest<CustomAuthEntity> {
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
}
