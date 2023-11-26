package com.ms.crud_api.model.response.user;

import com.ms.crud_api.model.entity.UserEntity;

import java.io.Serializable;

public class UserLoginResponse implements Serializable {
    private final Long id;
    private final String username;

    public UserLoginResponse(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public static UserLoginResponse fromEntity(UserEntity entity) {
        return new UserLoginResponse(
                entity.getId(),
                entity.getUsername()
        );
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
