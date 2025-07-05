package com.ms.learn.java.records.sb.infrastructure.model.response.body;

import com.fasterxml.jackson.annotation.JsonInclude;

public record BodyResponse(@JsonInclude(JsonInclude.Include.NON_NULL) Object data) {
}
