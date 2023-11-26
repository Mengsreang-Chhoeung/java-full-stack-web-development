package com.ms.crud_api.model.entity;

import com.ms.crud_api.infrastructure.model.entity.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tags")
public class TagEntity extends BaseEntity<Long> {
    @Column(length = 15, nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<ProductEntity> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TagEntity tagEntity = (TagEntity) o;
        return getId() != null && Objects.equals(getId(), tagEntity.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
