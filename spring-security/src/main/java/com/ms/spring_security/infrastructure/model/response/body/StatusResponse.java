package com.ms.spring_security.infrastructure.model.response.body;

public class StatusResponse {
    private Short code;
    private String message;

    public Short getCode() {
        return code;
    }

    public void setCode(Short code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
