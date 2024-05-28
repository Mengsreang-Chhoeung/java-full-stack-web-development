package com.ms.spring_file_upload.infrastructure.model.body;

public class StatusResponse {
    private Short code;
    private String message;

    public Short getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(Short code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
