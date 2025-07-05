package com.ms.learn.java.records.sb.modules.category.entity.request;

import com.ms.learn.java.records.sb.modules.category.entity.model.CategoryEntity;

public record CategoryRequest(String name, String description) {
    public CategoryEntity toEntity() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(this.name());
        categoryEntity.setDescription(this.description());

        return categoryEntity;
    }
}
