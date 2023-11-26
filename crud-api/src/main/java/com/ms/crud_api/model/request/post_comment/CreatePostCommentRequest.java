package com.ms.crud_api.model.request.post_comment;

import com.ms.crud_api.model.entity.PostCommentEntity;

import java.io.Serializable;

public class CreatePostCommentRequest implements Serializable {
    private String comment;
    private Long postId;

    public PostCommentEntity toEntity() {
        PostCommentEntity postComment = new PostCommentEntity();
        postComment.setComment(this.comment);

        return postComment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
