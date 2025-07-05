package com.ms.learn.java.records.sb.infrastructure.model.response.body;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;

public record BaseBodyResponse(@JsonInclude(JsonInclude.Include.NON_NULL) Boolean success,
        @JsonInclude(JsonInclude.Include.NON_NULL) BodyResponse body,
        @JsonInclude(JsonInclude.Include.NON_NULL) StatusResponse status) {

    public static ResponseEntity<BaseBodyResponse> failed(HttpStatusCode _status, String _message) {
        StatusResponse statusResponse = new StatusResponse((short) _status.value(), _message);
        BaseBodyResponse bodyResponse = new BaseBodyResponse(false, null, statusResponse);

        return ResponseEntity.status(_status).body(bodyResponse);
    }

    public static ResponseEntity<Object> internalFailed(HttpStatusCode _status, String _message) {
        StatusResponse statusResponse = new StatusResponse((short) _status.value(), _message);
        BaseBodyResponse bodyResponse = new BaseBodyResponse(false, null, statusResponse);

        return ResponseEntity.status(_status).body(bodyResponse);
    }

    public static ResponseEntity<BaseBodyResponse> success(Object data, String message) {
        short statusCode = 200;
        StatusResponse statusResponse = new StatusResponse(statusCode, message != null ? message : "Success");
        BodyResponse bodyResponse = new BodyResponse(data);
        BaseBodyResponse baseBodyResponse = new BaseBodyResponse(true, bodyResponse, statusResponse);

        return ResponseEntity.status(statusCode).body(baseBodyResponse);
    }
}
