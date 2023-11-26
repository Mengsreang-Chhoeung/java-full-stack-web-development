package com.ms.crud_api.model.response.tag;

import com.ms.crud_api.model.entity.TagEntity;

import java.io.Serializable;

public class TagResponse implements Serializable {
    private final Long id;
    private final String name;

    public TagResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static TagResponse fromEntity(TagEntity entity) {
        if (entity == null) return null;

        return new TagResponse(entity.getId(), entity.getName());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
