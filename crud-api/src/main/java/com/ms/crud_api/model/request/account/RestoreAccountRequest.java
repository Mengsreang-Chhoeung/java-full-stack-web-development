package com.ms.crud_api.model.request.account;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class RestoreAccountRequest implements Serializable {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Computer", maxLength = 50)
    @NotNull(message = "Name is required!")
    @NotEmpty(message = "Name cannot be empty!")
    @Size(max = 50, message = "Name cannot be bigger than 50 characters!")
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
