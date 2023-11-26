package com.ms.crud_api.model.request.post;

import com.ms.crud_api.model.entity.PostEntity;

import java.io.Serializable;

public class PostRequest implements Serializable {
    private String title;
    private String description;

    public PostEntity toEntity() {
        PostEntity post = new PostEntity();
        post.setTitle(this.getTitle());
        post.setDescription(this.getDescription());

        return post;
    }

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
}
