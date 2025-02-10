package com.ms.spring_security_jwt.infrastructure.service.implementation;

import com.ms.spring_security_jwt.infrastructure.annotation.transaction.ReadOnlyTransaction;
import com.ms.spring_security_jwt.infrastructure.exception.BadRequestException;
import com.ms.spring_security_jwt.infrastructure.exception.InternalServerErrorException;
import com.ms.spring_security_jwt.infrastructure.exception.NotFoundException;
import com.ms.spring_security_jwt.infrastructure.model.entity.BaseSoftDeleteEntity;
import com.ms.spring_security_jwt.infrastructure.model.request.BaseRequest;
import com.ms.spring_security_jwt.infrastructure.service.BaseCrudService;
import com.ms.spring_security_jwt.infrastructure.service.BaseService;
import com.ms.spring_security_jwt.infrastructure.service.UrlParamService;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ReadOnlyTransaction
public abstract class BaseCrudServiceImpl<T, ID extends Serializable> implements BaseCrudService<T, ID>, BaseService<T, ID> {
    @Transactional
    @Override
    public T create(BaseRequest<T> req) {
        return getRepository().save(req.toEntity());
    }

    @Transactional
    @Override
    public T update(ID id, BaseRequest<T> req) {
        return getRepository().save(req.toEntity());
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public Page<T> findAll(UrlParamService param) {
        return getRepository().findAll(param.getPageable());
    }

    @Override
    public Optional<T> findOne(ID id) {
        return getRepository().findById(id);
    }

    @Transactional
    @Override
    public void delete(ID id) {
        try {
            getRepository().deleteById(id);
        } catch (Exception ex) {
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @Transactional
    @Override
    public void softDelete(ID id) {
        try {
            getRepository().softDelete(id);
        } catch (NotFoundException ex) {
            throw new NotFoundException(ex.getMessage());
        } catch (Exception ex) {
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @Transactional
    @Override
    public void softDelete(T data) {
        if (BaseSoftDeleteEntity.class.isAssignableFrom(data.getClass())) {
            ((BaseSoftDeleteEntity) data).setDeletedAt(new Date());

            this.getRepository().save(data);
        } else throw new BadRequestException("soft deleted not available for this entity!");
    }

    @Transactional
    @Override
    public void restore(ID id) {
        try {
            getRepository().restore(id);
        } catch (NotFoundException ex) {
            throw new NotFoundException(ex.getMessage());
        } catch (Exception ex) {
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @Override
    public List<T> findAllAvailable() {
        return getRepository().findAllAvailable();
    }

    @Override
    public Page<T> findAllAvailable(UrlParamService param) {
        return getRepository().findAllAvailable(param);
    }

    @Override
    public Optional<T> findOneAvailable(ID id) {
        return getRepository().findOneAvailable(id);
    }

    @Override
    public Boolean existByIdAvailable(ID id) {
        return getRepository().existByIdAvailable(id);
    }

    @Override
    public Boolean existByFieldAvailable(String attributeName, String attributeValue) {
        return getRepository().existByFieldAvailable(attributeName, attributeValue);
    }
}
