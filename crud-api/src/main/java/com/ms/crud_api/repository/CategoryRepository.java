package com.ms.crud_api.repository;

import com.ms.crud_api.model.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>, JpaSpecificationExecutor<CategoryEntity> {
    boolean existsByName(String name);

    boolean existsByNameAndDeletedAtIsNull(String name);

    @Query("""
            select c from Category c
            where upper(c.name) like upper(concat('%', :q, '%')) or upper(c.description) like upper(concat('%', :q, '%'))""")
    List<CategoryEntity> findAllByName(@Param("q") String q);

    @Query(value = "SELECT * FROM categories", nativeQuery = true)
    List<CategoryEntity> findAllByUsingNativeQuery();

    @Query(value = "SELECT * FROM categories WHERE ( UPPER(name) LIKE UPPER(CONCAT('%', :q, '%')) OR UPPER(description) LIKE UPPER(CONCAT('%', :q, '%')) )", nativeQuery = true)
    List<CategoryEntity> findAllByNameUsingNativeQuery(@Param("q") String q);

    @Query(value = "select c from Category c order by c.id asc")
    List<CategoryEntity> findAllOrderByIdAsc();

    @Query(value = "select c from Category c order by c.id desc")
    List<CategoryEntity> findAllOrderByIdDesc();

    @Query(value = "select c from Category c order by c.name asc")
    List<CategoryEntity> findAllOrderByNameAsc();

    @Query(value = "select c from Category c order by c.name desc")
    List<CategoryEntity> findAllOrderByNameDesc();

    @Query("select c from Category c where upper(c.name) like upper(concat('%', :name, '%')) order by c.name")
    List<CategoryEntity> findAllByNameContainingIgnoreCaseOrderByNameAsc(@Param("name") String name);

    @Query("select c from Category c where upper(c.name) like upper(concat('%', :name, '%')) order by c.name DESC")
    List<CategoryEntity> findAllByNameContainingIgnoreCaseOrderByNameDesc(@Param("name") String name);

    @Query(value = "SELECT * FROM categories ORDER BY name ASC", nativeQuery = true)
    List<CategoryEntity> findAllOrderByNameAscUsingNativeQuery();

    @Query(value = "SELECT * FROM categories ORDER BY name DESC", nativeQuery = true)
    List<CategoryEntity> findAllOrderByNameDescUsingNativeQuery();

    @Query(value = "SELECT * FROM categories WHERE UPPER(name) LIKE UPPER(CONCAT('%', :name, '%')) ORDER BY name ASC", nativeQuery = true)
    List<CategoryEntity> findAllByNameContainingIgnoreCaseOrderByNameAscUsingNativeQuery(@Param("name") String name);

    @Query(value = "SELECT * FROM categories WHERE UPPER(name) LIKE UPPER(CONCAT('%', :name, '%')) ORDER BY name DESC", nativeQuery = true)
    List<CategoryEntity> findAllByNameContainingIgnoreCaseOrderByNameDescUsingNativeQuery(@Param("name") String name);

    List<CategoryEntity> findAllCategoriesByNameContainingIgnoreCase(String q, Sort sort);

    @Query("select c from Category c where upper(c.name) like upper(concat('%', ?1, '%'))")
    Page<CategoryEntity> findAllCategoriesByNameContainingIgnoreCase(Pageable pageable, String q);

    Optional<CategoryEntity> findByIdAndDeletedAtIsNull(Long id);
}
