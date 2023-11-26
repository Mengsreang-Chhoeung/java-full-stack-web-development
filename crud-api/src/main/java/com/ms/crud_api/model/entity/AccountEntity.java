package com.ms.crud_api.model.entity;

import com.ms.crud_api.infrastructure.model.entity.BaseSoftDeleteEntity;
import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "accounts")
public class AccountEntity extends BaseSoftDeleteEntity<Long> {
    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = false)
    private Double balance;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private Set<AccountHistoryEntity> accountHistories;

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

    public Set<AccountHistoryEntity> getAccountHistories() {
        return accountHistories;
    }

    public void setAccountHistories(Set<AccountHistoryEntity> accountHistories) {
        this.accountHistories = accountHistories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AccountEntity tagEntity = (AccountEntity) o;
        return getId() != null && Objects.equals(getId(), tagEntity.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
