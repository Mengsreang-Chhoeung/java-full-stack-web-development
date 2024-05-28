package com.ms.spring_file_upload.infrastructure.model.body;

import com.fasterxml.jackson.annotation.JsonInclude;

public class BodyResponse {
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Object data;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private PageResponse page;

    public Object getData() {
        return data;
    }

    public PageResponse getPage() {
        return page;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setPage(PageResponse page) {
        this.page = page;
    }
}
