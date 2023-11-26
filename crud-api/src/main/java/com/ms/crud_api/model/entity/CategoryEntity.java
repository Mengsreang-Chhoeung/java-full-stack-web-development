package com.ms.crud_api.model.entity;

import com.ms.crud_api.infrastructure.model.entity.BaseAuditEntity;
import com.ms.crud_api.infrastructure.model.entity.BaseEntity;
import com.ms.crud_api.infrastructure.model.entity.BaseSoftDeleteEntity;
import com.ms.crud_api.model.entity.listener.CategoryEntityListener;
import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity(name = "Category")
@Table(name = "categories")
@EntityListeners(CategoryEntityListener.class)
public class CategoryEntity extends BaseSoftDeleteEntity<Long> {
    @Column(nullable = false, length = 30)
    private String name;

    @Column(length = 100)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CategoryEntity that = (CategoryEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

//    @PrePersist
//    public void beforeSave() {
//        Logger log = Logger.getLogger(this.getClass().getName());
//        log.info("Before Category Persisted: ID = " + this.getId() + " and Name = " + this.getName() + " and Description = " + this.getDescription());
//    }
//
//    @PostPersist
//    public void afterSave() {
//        Logger log = Logger.getLogger(this.getClass().getName());
//        log.info("After Category Persisted: ID = " + this.getId() + " and Name = " + this.getName() + " and Description = " + this.getDescription());
//    }
//
//    @PreUpdate
//    public void beforeUpdate() {
//        Logger log = Logger.getLogger(this.getClass().getName());
//        log.info("Before Category Merged: ID = " + this.getId() + " and Name = " + this.getName() + " and Description = " + this.getDescription());
//    }
//
//    @PostUpdate
//    public void afterUpdate() {
//        Logger log = Logger.getLogger(this.getClass().getName());
//        log.info("After Category Merged: ID = " + this.getId() + " and Name = " + this.getName() + " and Description = " + this.getDescription());
//    }
//
//    @PreRemove
//    public void beforeDelete() {
//        Logger log = Logger.getLogger(this.getClass().getName());
//        log.info("Before Category Removed: ID = " + this.getId() + " and Name = " + this.getName() + " and Description = " + this.getDescription());
//    }
//
//    @PostRemove
//    public void afterDelete() {
//        Logger log = Logger.getLogger(this.getClass().getName());
//        log.info("After Category Removed: ID = " + this.getId() + " and Name = " + this.getName() + " and Description = " + this.getDescription());
//    }
//
//    @PostLoad
//    public void load() {
//        Logger log = Logger.getLogger(this.getClass().getName());
//        log.info("Category Loaded: ID = " + this.getId() + " and Name = " + this.getName() + " and Description = " + this.getDescription());
//    }
}
