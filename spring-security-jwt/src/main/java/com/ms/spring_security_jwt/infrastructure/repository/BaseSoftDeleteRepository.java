package com.ms.spring_security_jwt.infrastructure.repository;

import com.ms.spring_security_jwt.infrastructure.service.UrlParamService;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseSoftDeleteRepository<T, ID extends Serializable> {
    Optional<T> findOneAvailable(ID id);

    List<T> findAllAvailable();

    Page<T> findAllAvailable(UrlParamService urlParamService);

    void restore(ID id);

    void softDelete(ID id);

    Boolean existByIdAvailable(ID id);

    Boolean existByFieldAvailable(String attributeName, String attributeValue);
}
