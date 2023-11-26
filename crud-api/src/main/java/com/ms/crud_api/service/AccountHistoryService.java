package com.ms.crud_api.service;

import com.ms.crud_api.model.entity.AccountHistoryEntity;
import com.ms.crud_api.repository.AccountHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AccountHistoryService {
    private final AccountHistoryRepository accountHistoryRepository;

    @Autowired
    public AccountHistoryService(AccountHistoryRepository accountHistoryRepository) {
        this.accountHistoryRepository = accountHistoryRepository;
    }

    @Transactional
    public AccountHistoryEntity create(AccountHistoryEntity entity) throws Exception {
        try {
            return this.accountHistoryRepository.save(entity);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
}
