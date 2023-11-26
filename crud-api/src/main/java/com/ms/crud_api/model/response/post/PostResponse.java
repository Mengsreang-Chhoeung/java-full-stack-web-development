package com.ms.crud_api.model.response.post;

import com.ms.crud_api.model.entity.PostEntity;

import java.io.Serializable;

public class PostResponse implements Serializable {
    private Long id;
    private String title;
    private String description;

    public PostResponse(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public static PostResponse fromEntity(PostEntity entity) {
        return new PostResponse(entity.getId(), entity.getTitle(), entity.getDescription());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
