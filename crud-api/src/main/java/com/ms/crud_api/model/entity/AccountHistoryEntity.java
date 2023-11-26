package com.ms.crud_api.model.entity;

import com.ms.crud_api.constant.enums.CrudTypeEnum;
import com.ms.crud_api.infrastructure.model.entity.BaseSoftDeleteEntity;
import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "account_histories")
public class AccountHistoryEntity extends BaseSoftDeleteEntity<Long> {
    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = false)
    private Double balance;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CrudTypeEnum type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public CrudTypeEnum getType() {
        return type;
    }

    public void setType(CrudTypeEnum type) {
        this.type = type;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AccountHistoryEntity tagEntity = (AccountHistoryEntity) o;
        return getId() != null && Objects.equals(getId(), tagEntity.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
