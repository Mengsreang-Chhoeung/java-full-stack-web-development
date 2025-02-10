package com.ms.spring_security_jwt.modules.user.service;

import com.ms.spring_security_jwt.infrastructure.service.BaseCrudService;
import com.ms.spring_security_jwt.modules.user.model.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends BaseCrudService<UserEntity, Long> {
}
