package com.ms.crud_api.model.entity.listener;

import com.ms.crud_api.model.entity.CategoryEntity;
import jakarta.persistence.*;

import java.util.logging.Logger;

public class CategoryEntityListener {
    private final Logger log = Logger.getLogger(this.getClass().getName());

    @PrePersist
    public void beforeSave(CategoryEntity entity) {
        log.info("Before Category Persisted: ID = " + entity.getId() + " and Name = " + entity.getName() + " and Description = " + entity.getDescription());
    }

    @PostPersist
    public void afterSave(CategoryEntity entity) {
        log.info("After Category Persisted: ID = " + entity.getId() + " and Name = " + entity.getName() + " and Description = " + entity.getDescription());
    }

    @PreUpdate
    public void beforeUpdate(CategoryEntity entity) {
        log.info("Before Category Merged: ID = " + entity.getId() + " and Name = " + entity.getName() + " and Description = " + entity.getDescription());
    }

    @PostUpdate
    public void afterUpdate(CategoryEntity entity) {
        log.info("After Category Merged: ID = " + entity.getId() + " and Name = " + entity.getName() + " and Description = " + entity.getDescription());
    }

    @PreRemove
    public void beforeDelete(CategoryEntity entity) {
        log.info("Before Category Removed: ID = " + entity.getId() + " and Name = " + entity.getName() + " and Description = " + entity.getDescription());
    }

    @PostRemove
    public void afterDelete(CategoryEntity entity) {
        log.info("After Category Removed: ID = " + entity.getId() + " and Name = " + entity.getName() + " and Description = " + entity.getDescription());
    }

    @PostLoad
    public void load(CategoryEntity entity) {
        log.info("Category Loaded: ID = " + entity.getId() + " and Name = " + entity.getName() + " and Description = " + entity.getDescription());
    }
}
