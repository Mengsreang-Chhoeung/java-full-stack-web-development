package com.ms.crud_api.infrastructure.model.response.body;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ms.crud_api.infrastructure.model.response.BaseResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;

public class BaseBodyResponse implements Serializable {
    private final Object data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final PageResponse page;
    private final StatusResponse status;

    private BaseBodyResponse(Object data, PageResponse page, StatusResponse status) {
        this.data = data;
        this.page = page;
        this.status = status;
    }

    public static ResponseEntity<BaseBodyResponse> success(Page<BaseResponse> response, String message) {
        List<BaseResponse> data = response.getContent();
        PageResponse page;
        if (response.getPageable().isUnpaged()) page = null;
        else
            page = new PageResponse(response.getNumber() + 1, response.getSize(), response.getTotalPages(), response.getTotalElements());
        StatusResponse status = new StatusResponse(message, (short) 200);

        return ResponseEntity.ok(new BaseBodyResponse(data, page, status));
    }

    public static ResponseEntity<BaseBodyResponse> success(BaseResponse response, String message) {
        StatusResponse status = new StatusResponse(message, (short) 200);

        return ResponseEntity.ok(new BaseBodyResponse(response, null, status));
    }

    public static ResponseEntity<BaseBodyResponse> createSuccess(BaseResponse response, String message) {
        StatusResponse status = new StatusResponse(message, (short) 201);

        return ResponseEntity.ok(new BaseBodyResponse(response, null, status));
    }

    public Object getData() {
        return data;
    }

    public PageResponse getPage() {
        return page;
    }

    public StatusResponse getStatus() {
        return status;
    }
}
