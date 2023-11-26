package com.ms.crud_api.model.request.post_comment;

import java.io.Serializable;

public class UpdatePostCommentRequest implements Serializable {
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
