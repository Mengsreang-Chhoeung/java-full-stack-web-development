package com.ms.spring_security_jwt.infrastructure.model.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
abstract public class BaseSoftDeleteEntity extends BaseAuditEntity {
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;
}
