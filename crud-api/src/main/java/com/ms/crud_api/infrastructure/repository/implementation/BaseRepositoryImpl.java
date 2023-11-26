package com.ms.crud_api.infrastructure.repository.implementation;

import com.ms.crud_api.infrastructure.repository.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
    private final EntityManager entityManager;

    @Autowired
    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public boolean existsByField(String fieldName, Object value, Boolean withDeletedAt) {
        // 1. Create Builder from EntityManager
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

        // 2. Create Query from Builder
        CriteriaQuery<Long> cQuery = builder.createQuery(Long.class);

        // 3. Find root from Query Builder
        Root<T> root = cQuery.from(this.getDomainClass());

        Predicate fieldPredicate = builder.equal(root.get(fieldName), value);
        Predicate deletedAtPredicate = builder.isNull(root.get("deletedAt"));
        List<Predicate> predicateList = new ArrayList<>();
        predicateList.add(fieldPredicate);

        if (withDeletedAt) {
            predicateList.add(deletedAtPredicate);
        }

        // 4. Starting Query
        cQuery.select(builder.countDistinct(root)).where(predicateList.toArray(Predicate[]::new));

        // 5. Create Query from EntityManager
        TypedQuery<Long> query = this.entityManager.createQuery(cQuery);

        Long result = query.getSingleResult();

        return result > 0;
    }
}
