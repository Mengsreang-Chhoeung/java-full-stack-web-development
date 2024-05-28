package com.ms.spring_file_upload.infrastructure.model.body;

public class PageResponse {
    private int totalPage;
    private int page;
    private long totalCount;
    private int pageSize;

    public int getTotalPage() {
        return totalPage;
    }

    public int getPage() {
        return page;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
