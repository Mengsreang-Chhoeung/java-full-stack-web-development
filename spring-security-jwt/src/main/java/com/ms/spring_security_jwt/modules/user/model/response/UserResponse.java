package com.ms.spring_security_jwt.modules.user.model.response;

import com.ms.spring_security_jwt.constant.enums.UserRoleEnum;
import com.ms.spring_security_jwt.infrastructure.model.response.BaseResponse;
import com.ms.spring_security_jwt.modules.user.model.entity.UserEntity;
import lombok.Data;

@Data
public class UserResponse implements BaseResponse {
    private Long id;

    private Long createdAt;

    private String username;

    private String email;

    private String avatar;

    private UserRoleEnum role;

    public static UserResponse toResponse(UserEntity entity) {
        if (entity == null) return null;

        UserResponse userResponse = new UserResponse();
        userResponse.setId(entity.getId());
        userResponse.setCreatedAt(entity.getCreatedAt().getTime());
        userResponse.setUsername(entity.getUsername());
        userResponse.setEmail(entity.getEmail());
        userResponse.setAvatar(entity.getAvatar());
        userResponse.setRole(entity.getRole());

        return userResponse;
    }
}
