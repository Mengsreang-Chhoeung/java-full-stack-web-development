package com.ms.spring_security_jwt.modules.auth.model.request;

import com.ms.spring_security_jwt.infrastructure.model.request.BaseRequest;
import com.ms.spring_security_jwt.modules.user.model.entity.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BackendAuthLoginRequest implements BaseRequest<UserEntity> {
    @NotEmpty(message = "Username is required!")
    @Size(min = 1, max = 30, message = "The length of username must be between 1 and 30 characters!")
    private String username;

    @NotEmpty(message = "Password is required!")
    private String password;

    @Override
    public UserEntity toEntity() {
        return null;
    }
}
