package com.ms.spring_security_jwt.modules.auth.model.request;

import com.ms.spring_security_jwt.infrastructure.model.request.BaseRequest;
import com.ms.spring_security_jwt.modules.user.model.entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FrontendAuthLoginRequest implements BaseRequest<UserEntity> {
    @Email(message = "Email is invalid!")
    @NotEmpty(message = "Email is required!")
    @Size(min = 1, max = 255, message = "The length of username must be between 1 and 255 characters!")
    private String email;

    @NotEmpty(message = "Password is required!")
    private String password;

    @Override
    public UserEntity toEntity() {
        return null;
    }
}
