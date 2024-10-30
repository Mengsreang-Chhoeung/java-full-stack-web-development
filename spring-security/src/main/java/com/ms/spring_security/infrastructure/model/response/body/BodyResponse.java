package com.ms.spring_security.infrastructure.model.response.body;

import com.fasterxml.jackson.annotation.JsonInclude;

public class BodyResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PageResponse page;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public PageResponse getPage() {
        return page;
    }

    public void setPage(PageResponse page) {
        this.page = page;
    }
}
