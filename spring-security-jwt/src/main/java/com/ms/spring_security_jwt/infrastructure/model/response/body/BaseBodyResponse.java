package com.ms.spring_security_jwt.infrastructure.model.response.body;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ms.spring_security_jwt.infrastructure.model.response.BaseResponse;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;

@Data
public class BaseBodyResponse implements Serializable {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean success;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BodyResponse body;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StatusResponse status;

    public static ResponseEntity<BaseBodyResponse> success(Object data, String message) {
        short statusCode = 200;
        BaseBodyResponse baseBodyResponse = new BaseBodyResponse();

        StatusResponse statusResponse = new StatusResponse(statusCode, message != null ? message : "Success");

        BodyResponse bodyResponse = new BodyResponse();
        if (data instanceof BaseResponse) {
            bodyResponse.setData(data);
        }

        baseBodyResponse.setSuccess(true);
        baseBodyResponse.setStatus(statusResponse);
        baseBodyResponse.setBody(bodyResponse);

        return ResponseEntity.status(statusCode).body(baseBodyResponse);
    }

    public static ResponseEntity<BaseBodyResponse> success(List<BaseResponse> data, String message) {
        short statusCode = 200;
        BaseBodyResponse baseBodyResponse = new BaseBodyResponse();

        StatusResponse statusResponse = new StatusResponse(statusCode, message != null ? message : "Success");

        BodyResponse bodyResponse = new BodyResponse(data, null);

        baseBodyResponse.setSuccess(true);
        baseBodyResponse.setStatus(statusResponse);
        baseBodyResponse.setBody(bodyResponse);

        return ResponseEntity.status(statusCode).body(baseBodyResponse);
    }

    public static ResponseEntity<BaseBodyResponse> success(String message) {
        short statusCode = 200;
        BaseBodyResponse baseBodyResponse = new BaseBodyResponse();

        StatusResponse statusResponse = new StatusResponse(statusCode, message != null ? message : "Success");

        baseBodyResponse.setSuccess(true);
        baseBodyResponse.setStatus(statusResponse);

        return ResponseEntity.status(statusCode).body(baseBodyResponse);
    }

    public static ResponseEntity<BaseBodyResponse> pageSuccess(Page<BaseResponse> page, String message) {
        short statusCode = 200;
        BaseBodyResponse baseBodyResponse = new BaseBodyResponse();

        PageResponse pageResponse;
        if (page.getPageable().isUnpaged()) pageResponse = null;
        else
            pageResponse = new PageResponse(page.getTotalPages(), page.getNumber(), page.getTotalElements(), page.getSize());

        StatusResponse statusResponse = new StatusResponse(statusCode, message != null ? message : "Success");

        BodyResponse bodyResponse = new BodyResponse(page.getContent(), pageResponse);

        baseBodyResponse.setSuccess(true);
        baseBodyResponse.setStatus(statusResponse);
        baseBodyResponse.setBody(bodyResponse);

        return ResponseEntity.status(statusCode).body(baseBodyResponse);
    }

    public static ResponseEntity<BaseBodyResponse> failed(HttpStatusCode status, String message) {
        StatusResponse statusResponse = new StatusResponse((short) status.value(), message != null ? message : "Failed");

        BaseBodyResponse bodyResponse = new BaseBodyResponse();
        bodyResponse.setSuccess(false);
        bodyResponse.setStatus(statusResponse);

        return ResponseEntity.status(status).body(bodyResponse);
    }

    public static ResponseEntity<Object> internalFailed(HttpStatusCode status, String message) {
        StatusResponse statusResponse = new StatusResponse((short) status.value(), message != null ? message : "Failed");

        BaseBodyResponse bodyResponse = new BaseBodyResponse();
        bodyResponse.setSuccess(false);
        bodyResponse.setStatus(statusResponse);

        return ResponseEntity.status(status).body(bodyResponse);
    }

    public static BaseBodyResponse bodyFailed(HttpStatusCode status, String message) {
        StatusResponse statusResponse = new StatusResponse((short) status.value(), message != null ? message : "Failed");

        BaseBodyResponse bodyResponse = new BaseBodyResponse();
        bodyResponse.setSuccess(false);
        bodyResponse.setStatus(statusResponse);

        return bodyResponse;
    }
}
