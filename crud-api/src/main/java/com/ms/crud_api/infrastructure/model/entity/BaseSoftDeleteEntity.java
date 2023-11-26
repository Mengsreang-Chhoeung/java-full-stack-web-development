package com.ms.crud_api.infrastructure.model.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class BaseSoftDeleteEntity<ID extends Serializable> extends BaseAuditEntity<ID> {
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}
