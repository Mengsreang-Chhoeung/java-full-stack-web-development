package com.ms.crud_api.repository;

import com.ms.crud_api.model.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {
    boolean existsByName(String name);
}
