package com.ms.spring_security_jwt.infrastructure.model.response.body;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StatusResponse {
    @Schema(example = "200")
    public Short code;
    @Schema(example = "Success")
    public String message;
}
