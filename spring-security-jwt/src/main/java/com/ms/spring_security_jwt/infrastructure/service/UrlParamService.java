package com.ms.spring_security_jwt.infrastructure.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

public interface UrlParamService {
    Pageable getPageable();

    <T> Specification<T> getSearch();

    void setInternalSearch(String search);

    void setAllowedSearchFields(Set<String> fields);

    Sort getSort();
}
