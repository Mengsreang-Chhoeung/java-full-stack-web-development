package com.ms.learn.java.records.sb.modules.category.entity.response;

import com.ms.learn.java.records.sb.modules.category.entity.model.CategoryEntity;

public record CategoryResponse(Long id, String name, String description) {
    public static CategoryResponse toResponse(CategoryEntity entity) {
        if (entity == null)
            return null;

        return new CategoryResponse(entity.getId(), entity.getName(), entity.getDescription());
    }
}
