package com.ms.spring_security_jwt.infrastructure.repository.implementation;

import com.ms.spring_security_jwt.infrastructure.exception.BadRequestException;
import com.ms.spring_security_jwt.infrastructure.exception.NotFoundException;
import com.ms.spring_security_jwt.infrastructure.model.entity.BaseSoftDeleteEntity;
import com.ms.spring_security_jwt.infrastructure.repository.BaseRepository;
import com.ms.spring_security_jwt.infrastructure.service.UrlParamService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
    private final EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<T> findOneAvailable(ID id) {
        if (BaseSoftDeleteEntity.class.isAssignableFrom(this.getDomainClass())) {
            CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = builder.createQuery(this.getDomainClass());
            Root<T> root = criteriaQuery.from(this.getDomainClass());

            criteriaQuery.select(root).where(builder.and(builder.equal(root.get("id"), id), builder.isNull(root.get("deletedAt"))));

            TypedQuery<T> query = this.entityManager.createQuery(criteriaQuery).setFirstResult(0).setMaxResults(1);
            List<T> result = query.getResultList();
            if (result.size() > 0) return Optional.of(result.get(0));
            else return Optional.empty();
        }

        return Optional.empty();
    }

    @Override
    public List<T> findAllAvailable() {
        if (BaseSoftDeleteEntity.class.isAssignableFrom(this.getDomainClass())) {
            CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = builder.createQuery(this.getDomainClass());
            Root<T> root = criteriaQuery.from(this.getDomainClass());

            criteriaQuery.select(root).where(builder.isNull(root.get("deletedAt")));

            TypedQuery<T> query = this.entityManager.createQuery(criteriaQuery);
            return query.getResultList();
        }

        return null;
    }

    @Override
    public Page<T> findAllAvailable(UrlParamService urlParamService) {
        if (BaseSoftDeleteEntity.class.isAssignableFrom(this.getDomainClass())) {
            Pageable pageable = urlParamService.getPageable();
            Specification<T> search = urlParamService.getSearch();
            CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

            CriteriaQuery<T> criteriaQuery = builder.createQuery(this.getDomainClass());
            Root<T> root = criteriaQuery.from(this.getDomainClass());
            criteriaQuery.select(root).where(search.toPredicate(root, criteriaQuery, builder), builder.isNull(root.get("deletedAt"))).orderBy(QueryUtils.toOrders(pageable.getSort(), root, builder));

            TypedQuery<T> query = this.entityManager.createQuery(criteriaQuery);
            if (pageable.isPaged()) {
                query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
                query.setMaxResults(pageable.getPageSize());
            }

            CriteriaQuery<Long> criteriaQueryCount = builder.createQuery(Long.class);
            Root<T> rootCount = criteriaQueryCount.from(this.getDomainClass());
            criteriaQueryCount.select(builder.countDistinct(rootCount.get("id"))).where(search.toPredicate(rootCount, criteriaQueryCount, builder), builder.isNull(rootCount.get("deletedAt")));
            TypedQuery<Long> queryCount = this.entityManager.createQuery(criteriaQueryCount);

            List<T> result = query.getResultList();
            Long resultCount = queryCount.getSingleResult();
            return new PageImpl<>(result, pageable, resultCount);
        }

        return null;
    }

    @Override
    public void restore(ID id) {
        if (BaseSoftDeleteEntity.class.isAssignableFrom(this.getDomainClass())) {
            T data = this.findById(id).orElseThrow(() -> new NotFoundException("Not found!"));

            ((BaseSoftDeleteEntity) data).setDeletedAt(null);

            super.save(data);
        } else throw new BadRequestException("restore entity from soft deleted not available for this entity!");
    }

    @Override
    public void softDelete(ID id) {
        if (BaseSoftDeleteEntity.class.isAssignableFrom(this.getDomainClass())) {
            T data = this.findById(id).orElseThrow(() -> new NotFoundException("Not found!"));

            ((BaseSoftDeleteEntity) data).setDeletedAt(new Date());

            super.save(data);
        } else throw new BadRequestException("soft deleted not available for this entity!");
    }

    @Override
    public Boolean existByIdAvailable(ID id) {
        if (BaseSoftDeleteEntity.class.isAssignableFrom(this.getDomainClass())) {
            CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
            Root<T> root = criteriaQuery.from(this.getDomainClass());

            criteriaQuery.select(builder.countDistinct(root)).where(builder.and(builder.equal(root.get("id"), id), builder.isNull(root.get("deletedAt"))));

            TypedQuery<Long> query = this.entityManager.createQuery(criteriaQuery).setMaxResults(1).setFirstResult(0);

            return query.getSingleResult() > 0;
        }

        return false;
    }

    @Override
    public Boolean existByFieldAvailable(String attributeName, String attributeValue) {
        if (BaseSoftDeleteEntity.class.isAssignableFrom(this.getDomainClass())) {
            CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
            Root<T> root = criteriaQuery.from(this.getDomainClass());

            criteriaQuery.select(builder.countDistinct(root)).where(builder.and(builder.equal(root.get(attributeName), attributeValue), builder.isNull(root.get("deletedAt"))));

            TypedQuery<Long> query = this.entityManager.createQuery(criteriaQuery).setMaxResults(1).setFirstResult(0);

            return query.getSingleResult() > 0;
        }

        return false;
    }
}
