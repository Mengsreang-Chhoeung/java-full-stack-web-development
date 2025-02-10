package com.ms.spring_security_jwt.modules.user.model.request;

import com.ms.spring_security_jwt.constant.enums.UserRoleEnum;
import com.ms.spring_security_jwt.infrastructure.annotation.validation.enum_subset.EnumSubset;
import com.ms.spring_security_jwt.infrastructure.model.request.BaseRequest;
import com.ms.spring_security_jwt.modules.user.model.entity.UserEntity;
import com.ms.spring_security_jwt.util.StaticBeanUtil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BackendCreateUserRequest implements BaseRequest<UserEntity> {
    @NotEmpty(message = "Username is required!")
    @Size(min = 1, max = 30, message = "The length of username must be between 1 and 30 characters!")
    private String username;

    @Email(message = "Email is invalid!")
    @NotEmpty(message = "Email is required!")
    @Size(min = 1, max = 255, message = "The length of username must be between 1 and 255 characters!")
    private String email;

    @NotEmpty(message = "Password is required!")
    private String password;

    @NotNull(message = "Role is required!")
    @EnumSubset(enumClass = UserRoleEnum.class, anyOf = {"CUSTOMER", "ADMIN"})
    private UserRoleEnum role;

    private String avatar;

    @Override
    public UserEntity toEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(this.getUsername());
        userEntity.setEmail(this.getEmail());
        userEntity.setPassword(StaticBeanUtil.getPasswordEncoder().encode(this.getPassword()));
        userEntity.setAvatar(this.getAvatar());
        userEntity.setRole(this.getRole());
        userEntity.setEnabled(true);

        return userEntity;
    }
}
