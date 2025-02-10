package com.ms.spring_security_jwt.infrastructure.model.response.api_docs.success;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public class SuccessResponse implements Serializable {
    @Schema(example = "true")
    public Boolean success;
    public SuccessStatusResponse status;
}