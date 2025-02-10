package com.ms.spring_security_jwt.modules.security.model.projection;

import com.ms.spring_security_jwt.constant.enums.UserRoleEnum;

public record UserAuthDetailsProjection (Long id, String username, String password, Boolean enabled, UserRoleEnum role) {}
