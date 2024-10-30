package com.ms.spring_security.infrastructure.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public interface BaseRequest<T> extends Serializable {
    @JsonIgnore
    T toEntity();
}
