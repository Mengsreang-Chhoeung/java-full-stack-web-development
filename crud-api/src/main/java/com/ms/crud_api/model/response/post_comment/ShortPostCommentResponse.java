package com.ms.crud_api.model.response.post_comment;

import com.ms.crud_api.model.entity.PostCommentEntity;

import java.io.Serializable;

public class ShortPostCommentResponse implements Serializable {
    private final Long id;
    private final String comment;

    public ShortPostCommentResponse(Long id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public static ShortPostCommentResponse fromEntity(PostCommentEntity entity) {
        return new ShortPostCommentResponse(entity.getId(), entity.getComment());
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }
}
