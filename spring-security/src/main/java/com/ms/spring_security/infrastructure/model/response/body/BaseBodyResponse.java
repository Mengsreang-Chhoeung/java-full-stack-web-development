package com.ms.spring_security.infrastructure.model.response.body;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ms.spring_security.infrastructure.model.response.BaseResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class BaseBodyResponse implements BaseResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean success;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BodyResponse body;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StatusResponse status;

    public static ResponseEntity<BaseBodyResponse> failed(HttpStatusCode _status, String _message) {
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setCode((short) _status.value());
        statusResponse.setMessage(_message);

        BaseBodyResponse bodyResponse = new BaseBodyResponse();
        bodyResponse.setSuccess(false);
        bodyResponse.setStatus(statusResponse);

        return ResponseEntity.status(_status).body(bodyResponse);
    }

    public static ResponseEntity<Object> internalFailed(HttpStatusCode _status, String _message) {
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setCode((short) _status.value());
        statusResponse.setMessage(_message);

        BaseBodyResponse bodyResponse = new BaseBodyResponse();
        bodyResponse.setSuccess(false);
        bodyResponse.setStatus(statusResponse);

        return ResponseEntity.status(_status).body(bodyResponse);
    }

    public static BaseBodyResponse bodyFailed(HttpStatusCode _status, String _message) {
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setCode((short) _status.value());
        statusResponse.setMessage(_message);

        BaseBodyResponse bodyResponse = new BaseBodyResponse();
        bodyResponse.setSuccess(false);
        bodyResponse.setStatus(statusResponse);

        return bodyResponse;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public BodyResponse getBody() {
        return body;
    }

    public void setBody(BodyResponse body) {
        this.body = body;
    }

//    public static ResponseEntity<BaseBodyResponse> success(Object data, Page<?> page, String message) {
//        short statusCode = 200;
//        BaseBodyResponse baseBodyResponse = new BaseBodyResponse();
//
//        StatusResponse statusResponse = new StatusResponse();
//        statusResponse.setCode(statusCode);
//        statusResponse.setMessage(message != null ? message : "Success");
//
//        BodyResponse bodyResponse = new BodyResponse();
//        if (data instanceof BaseResponse) {
//            bodyResponse.setData(data);
//        } else if (data instanceof List<?>) {
//            if (((List<?>) data).size() > 0 && ((List<?>) data).get(0) instanceof BaseResponse) {
//                bodyResponse.setData(data);
//            } else {
//                bodyResponse.setData(Collections.emptyList());
//            }
//        }
//
//        if (page != null) {
//            PageResponse pageResponse = new PageResponse();
//            pageResponse.setPage(page.getNumber());
//            pageResponse.setPageSize(page.getSize());
//            pageResponse.setTotalCount(page.getTotalElements());
//            pageResponse.setTotalPage((int) Math.ceil(((double) page.getTotalElements()) / ((double) page.getSize())));
//
//            bodyResponse.setPage(pageResponse);
//        }
//
//        baseBodyResponse.setSuccess(true);
//        baseBodyResponse.setStatus(statusResponse);
//        baseBodyResponse.setBody(bodyResponse);
//
//        return ResponseEntity.status(statusCode).body(baseBodyResponse);
//    }

    public static ResponseEntity<BaseBodyResponse> success(Object data, String message) {
        short statusCode = 200;
        BaseBodyResponse baseBodyResponse = new BaseBodyResponse();

        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setCode(statusCode);
        statusResponse.setMessage(message != null ? message : "Success");

        BodyResponse bodyResponse = new BodyResponse();
        if (data instanceof BaseResponse) {
            bodyResponse.setData(data);
        }

        baseBodyResponse.setSuccess(true);
        baseBodyResponse.setStatus(statusResponse);
        baseBodyResponse.setBody(bodyResponse);

        return ResponseEntity.status(statusCode).body(baseBodyResponse);
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }
}
