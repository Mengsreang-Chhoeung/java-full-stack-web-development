package com.ms.crud_api.repository;

import com.ms.crud_api.infrastructure.repository.BaseRepository;
import com.ms.crud_api.model.entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends BaseRepository<SkillEntity, Long>, JpaSpecificationExecutor<SkillEntity> {
}
