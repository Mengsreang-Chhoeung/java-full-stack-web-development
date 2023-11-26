package com.ms.crud_api.model.request.skill;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class RestoreSkillRequest implements Serializable {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Computer", maxLength = 30)
    @NotNull(message = "Name is required!")
    @NotEmpty(message = "Name cannot be empty!")
    @Size(max = 30, message = "Name cannot be bigger than 30 characters!")
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
