package com.ms.crud_api.model.response.account;

import com.ms.crud_api.infrastructure.model.response.BaseResponse;
import com.ms.crud_api.model.entity.AccountEntity;

public class AccountResponse extends BaseResponse {
    private final Long id;
    private final String name;
    private final Double balance;

    public AccountResponse(Long id, String name, Double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public static AccountResponse fromEntity(AccountEntity entity) {
        if (entity == null) return null;

        return new AccountResponse(entity.getId(), entity.getName(), entity.getBalance());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getBalance() {
        return balance;
    }
}
