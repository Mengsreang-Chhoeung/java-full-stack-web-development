package com.ms.crud_api.model.response.error;

import java.io.Serializable;

public class ErrorResponse implements Serializable {
    private String message;
    private Short status;

    public ErrorResponse(String message, Short status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public Short getStatus() {
        return status;
    }
}
