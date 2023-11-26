package com.ms.crud_api.infrastructure.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
