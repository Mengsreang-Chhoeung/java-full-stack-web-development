package com.ms.crud_api.model.request.category;

import com.ms.crud_api.model.entity.CategoryEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class CategoryRequest implements Serializable {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Computer", maxLength = 30)
    @NotNull(message = "Name is required!")
    @NotEmpty(message = "Name cannot be empty!")
    @Size(max = 30, message = "Name cannot be bigger than 30 characters!")
    private String name;
    @Size(max = 100, message = "Description cannot be bigger than 100 characters!")
    @Schema(example = "Computer Category", maxLength = 100, nullable = true)
    private String description;

    public CategoryEntity toEntity() {
        CategoryEntity category = new CategoryEntity();
        category.setName(this.name);
        category.setDescription(this.description);
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
