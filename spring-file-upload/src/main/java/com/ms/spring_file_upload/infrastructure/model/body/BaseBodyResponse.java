package com.ms.spring_file_upload.infrastructure.model.body;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ms.spring_file_upload.infrastructure.model.response.BaseResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class BaseBodyResponse implements Serializable {
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Boolean success;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private BodyResponse body;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private StatusResponse status;

    public Boolean getSuccess() {
        return success;
    }

    public BodyResponse getBody() {
        return body;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setBody(BodyResponse body) {
        this.body = body;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }

    public static ResponseEntity<BaseBodyResponse> success(Object data, Page<?> page, String message) {
        short statusCode = 200;
        BaseBodyResponse baseBodyResponse = new BaseBodyResponse();

        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setCode(statusCode);
        statusResponse.setMessage(message);

        BodyResponse bodyResponse = new BodyResponse();
        if (data instanceof BaseResponse) {
            bodyResponse.setData(data);
        } else if (data instanceof List<?>) {
            if (((List<?>) data).size() > 0 && ((List<?>) data).get(0) instanceof BaseResponse) {
                bodyResponse.setData(data);
            } else {
                bodyResponse.setData(Collections.emptyList());
            }
        }

        if (page != null) {
            PageResponse pageResponse = new PageResponse();
            pageResponse.setPage(page.getNumber());
            pageResponse.setPageSize(page.getSize());
            pageResponse.setTotalCount(page.getTotalElements());
            pageResponse.setTotalPage((int) Math.ceil(((double) page.getTotalElements()) / ((double) page.getSize())));

            bodyResponse.setPage(pageResponse);
        }

        baseBodyResponse.setSuccess(true);
        baseBodyResponse.setStatus(statusResponse);
        baseBodyResponse.setBody(bodyResponse);

        return ResponseEntity.status(statusCode).body(baseBodyResponse);
    }

    public static ResponseEntity<BaseBodyResponse> failed(String message, HttpStatusCode httpStatus) {
        BaseBodyResponse data = new BaseBodyResponse();
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setCode((short) httpStatus.value());
        statusResponse.setMessage(message);

        data.setSuccess(false);
        data.setStatus(statusResponse);

        return ResponseEntity.status(httpStatus).body(data);
    }

    public static ResponseEntity<Object> internalFailed(String message, HttpStatusCode httpStatus) {
        BaseBodyResponse data = new BaseBodyResponse();
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setCode((short) httpStatus.value());
        statusResponse.setMessage(message);

        data.setSuccess(false);
        data.setStatus(statusResponse);

        return ResponseEntity.status(httpStatus).body(data);
    }
}
