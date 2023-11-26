package com.ms.crud_api.infrastructure.model.response.body;

public class PageResponse {
    private int page;
    private int pageSize;
    private int totalPage;
    private long totalCount;

    protected PageResponse(int page, int pageSize, int totalPage, long totalCount) {
        this.page = page;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public long getTotalCount() {
        return totalCount;
    }
}
