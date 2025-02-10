package com.ms.spring_security_jwt.infrastructure.model.response.body;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PageResponse {
    public Integer totalPage;
    public Integer page;
    public Long totalCount;
    public Integer pageSize;
}