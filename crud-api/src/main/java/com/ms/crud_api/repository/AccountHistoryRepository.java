package com.ms.crud_api.repository;

import com.ms.crud_api.model.entity.AccountHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHistoryRepository extends JpaRepository<AccountHistoryEntity, Long> {
}
