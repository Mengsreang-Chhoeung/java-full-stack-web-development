package com.ms.crud_api.model.entity;

import com.ms.crud_api.infrastructure.model.entity.BaseSoftDeleteEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.Hibernate;
import org.hibernate.annotations.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "skills")
@SQLDelete(sql = "UPDATE skills SET deleted_at = NOW() WHERE id=?")
//@Where(clause = "deleted_at IS NULL")
@FilterDef(name = "deletedAt", parameters = @ParamDef(name = "isDeleted", type = Date.class))
@Filter(name = "deletedAt", condition = "deleted_at = :isDeleted")
public class SkillEntity extends BaseSoftDeleteEntity<Long> {
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
        SkillEntity that = (SkillEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
