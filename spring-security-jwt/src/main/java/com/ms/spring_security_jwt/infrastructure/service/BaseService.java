package com.ms.spring_security_jwt.infrastructure.service;

import com.ms.spring_security_jwt.infrastructure.repository.BaseRepository;

import java.io.Serializable;

public interface BaseService<T, ID extends Serializable> {
    BaseRepository<T, ID> getRepository();
}
