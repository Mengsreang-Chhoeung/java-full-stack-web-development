package com.ms.crud_api.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    boolean existsByField(String fieldName, Object value, Boolean withDeletedAt);
}
