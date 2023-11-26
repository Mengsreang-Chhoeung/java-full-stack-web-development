package com.ms.crud_api.model.response.post_comment;

import com.ms.crud_api.model.entity.PostCommentEntity;
import com.ms.crud_api.model.response.post.ShortPostResponse;

import java.io.Serializable;

public class PostCommentResponse implements Serializable {
    private Long id;
    private String comment;
    private ShortPostResponse post;

    public PostCommentResponse(Long id, String comment, ShortPostResponse post) {
        this.id = id;
        this.comment = comment;
        this.post = post;
    }

    public static PostCommentResponse fromEntity(PostCommentEntity entity) {
        return new PostCommentResponse(entity.getId(), entity.getComment(), ShortPostResponse.fromEntity(entity.getPost()));
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public ShortPostResponse getPost() {
        return post;
    }
}
