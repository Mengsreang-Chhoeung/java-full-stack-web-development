package com.ms.crud_api.repository;

import com.ms.crud_api.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long>, JpaSpecificationExecutor<AccountEntity> {
    boolean existsByNameAndDeletedAtIsNull(String name);

    Optional<AccountEntity> findByIdAndDeletedAtIsNull(Long id);
}
