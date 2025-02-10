package com.ms.spring_security_jwt.infrastructure.model.response.api_docs.success;

import io.swagger.v3.oas.annotations.media.Schema;

public class SuccessStatusResponse {
    @Schema(example = "200")
    public Short code;
    @Schema(example = "Success")
    public String message;
}
