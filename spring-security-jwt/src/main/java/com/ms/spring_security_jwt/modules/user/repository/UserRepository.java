package com.ms.spring_security_jwt.modules.user.repository;

import com.ms.spring_security_jwt.infrastructure.repository.BaseRepository;
import com.ms.spring_security_jwt.modules.user.model.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, Long> {
}
