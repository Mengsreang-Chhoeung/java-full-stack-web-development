package com.ms.spring_file_upload.infrastructure.model.body;


public class ErrorResponse {
    private Boolean success;
    private StatusResponse status;

    public Boolean getSuccess() {
        return success;
    }

    public StatusResponse getStatus() {
        return status;
    }
}
