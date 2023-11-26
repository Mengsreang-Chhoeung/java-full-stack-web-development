package com.ms.crud_api.model.response.category;

import com.ms.crud_api.infrastructure.model.response.BaseResponse;
import com.ms.crud_api.model.entity.CategoryEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public class CategoryResponse extends BaseResponse {
    @Schema(example = "168")
    private Long id;
    @Schema(example = "Best Category")
    private String name;
    @Schema(example = "Best Category Ever")
    private String description;

    public CategoryResponse(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static CategoryResponse fromEntity(CategoryEntity entity) {
        return new CategoryResponse(
                entity.getId(),
                entity.getName(),
                entity.getDescription()
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
