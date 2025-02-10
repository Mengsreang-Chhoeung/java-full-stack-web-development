package com.ms.spring_security_jwt.modules.user.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.spring_security_jwt.infrastructure.model.request.BaseRequest;
import com.ms.spring_security_jwt.modules.user.model.entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BackendUpdateUserRequest implements BaseRequest<UserEntity> {
    @NotEmpty(message = "Username is required!")
    @Size(min = 1, max = 30, message = "The length of username must be between 1 and 30 characters!")
    private String username;

    @Email(message = "Email is invalid!")
    @NotEmpty(message = "Email is required!")
    @Size(min = 1, max = 255, message = "The length of username must be between 1 and 255 characters!")
    private String email;

    private String avatar;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private UserEntity existingEntity;

    @Override
    public UserEntity toEntity() {
        if (this.getExistingEntity() == null) return null;

        UserEntity userEntity = this.getExistingEntity();
        userEntity.setUsername(this.getUsername());
        userEntity.setEmail(this.getEmail());
        userEntity.setAvatar(this.getAvatar());
        userEntity.setEnabled(true);
        userEntity.setPassword(this.getPassword());

        return userEntity;
    }
}
