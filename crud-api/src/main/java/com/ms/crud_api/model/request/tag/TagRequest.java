package com.ms.crud_api.model.request.tag;

import com.ms.crud_api.model.entity.TagEntity;

import java.io.Serializable;

public class TagRequest implements Serializable {
    private String name;

    public TagEntity toEntity() {
        TagEntity tag = new TagEntity();
        tag.setName(this.name);

        return tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
