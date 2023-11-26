package com.ms.crud_api.model.entity;

import com.ms.crud_api.infrastructure.model.entity.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "posts")
public class PostEntity extends BaseEntity<Long> {
    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 500, nullable = false)
    private String description;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PostCommentEntity> postComments;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        PostEntity that = (PostEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
