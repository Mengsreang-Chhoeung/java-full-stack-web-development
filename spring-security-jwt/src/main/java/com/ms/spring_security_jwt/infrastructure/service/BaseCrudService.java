package com.ms.spring_security_jwt.infrastructure.service;

import com.ms.spring_security_jwt.infrastructure.model.request.BaseRequest;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseCrudService<T, ID extends Serializable> {
    T create(BaseRequest<T> req);

    T update(ID id, BaseRequest<T> req);

    List<T> findAll();

    List<T> findAllAvailable();

    Page<T> findAll(UrlParamService param);

    Page<T> findAllAvailable(UrlParamService param);

    Optional<T> findOne(ID id);

    Optional<T> findOneAvailable(ID id);

    void delete(ID id);

    void softDelete(ID id);

    void softDelete(T data);

    void restore(ID id);

    Boolean existByIdAvailable(ID id);

    Boolean existByFieldAvailable(String attributeName, String attributeValue);
}
