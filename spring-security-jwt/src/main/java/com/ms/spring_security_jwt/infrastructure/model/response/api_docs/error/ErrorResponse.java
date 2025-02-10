package com.ms.spring_security_jwt.infrastructure.model.response.api_docs.error;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public class ErrorResponse implements Serializable {
    @Schema(example = "false")
    public Boolean success;
    public ErrorStatusResponse status;
}