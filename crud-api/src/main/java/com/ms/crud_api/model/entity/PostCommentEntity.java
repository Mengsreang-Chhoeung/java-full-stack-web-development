package com.ms.crud_api.model.entity;

import com.ms.crud_api.infrastructure.model.entity.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "post_comments")
public class PostCommentEntity extends BaseEntity<Long> {
    @Column(nullable = false, length = 250)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false, referencedColumnName = "id")
    private PostEntity post;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PostCommentEntity that = (PostCommentEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
