package com.ms.crud_api.service;

import com.ms.crud_api.exception.AlreadyExistException;
import com.ms.crud_api.exception.BadRequestException;
import com.ms.crud_api.exception.NotFoundException;
import com.ms.crud_api.model.entity.CategoryEntity;
import com.ms.crud_api.model.request.category.CategoryRequest;
import com.ms.crud_api.model.request.category.RestoreCategoryRequest;
import com.ms.crud_api.repository.CategoryRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryEntity create(CategoryRequest request) throws Exception {
        // prepare request to entity
        CategoryEntity data = request.toEntity();

        // check name from request exists or not in db
        if (this.categoryRepository.existsByNameAndDeletedAtIsNull(data.getName()))
            throw new AlreadyExistException("Category name already exists!");

        try {
            // save entity
            return this.categoryRepository.save(data);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public CategoryEntity update(Long id, CategoryRequest request) throws Exception {
        // check from data from database and if it isn't exist then throw error
        CategoryEntity foundData = this.findOne(id);

        // check name from request exists or not in db
        if (!Objects.equals(foundData.getName(), request.getName()))
            if (this.categoryRepository.existsByNameAndDeletedAtIsNull(request.getName()))
                throw new AlreadyExistException("Category name already exists!");

        // add request data to existing data
        // #1. need give all field's value to update
        foundData.setName(request.getName());
        foundData.setDescription(request.getDescription());

        // #2. update only field that specify
        // foundData.setName(request.getName() == null ? foundData.getName() : request.getName());
        // foundData.setDescription(request.getDescription() == null ? foundData.getDescription() : request.getDescription());

        try {
            // update entity
            return this.categoryRepository.save(foundData);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public Page<CategoryEntity> findAll(int page, int limit, boolean isPage, String sort, boolean isTrash, Map<String, String> reqParam) throws BadRequestException {
//        if (q == null) {
//            if (Objects.equals(sortName, "a-z")) return this.categoryRepository.findAllOrderByNameAscUsingNativeQuery();
//            else return this.categoryRepository.findAllOrderByNameDescUsingNativeQuery();
//        } else {
//            if (Objects.equals(sortName, "a-z"))
//                return this.categoryRepository.findAllByNameContainingIgnoreCaseOrderByNameAscUsingNativeQuery(q);
//            else return this.categoryRepository.findAllByNameContainingIgnoreCaseOrderByNameDescUsingNativeQuery(q);
//        }

//        if (q == null || q.equals("")) {
//            if (Objects.equals(sortName, "a-z")) return this.categoryRepository.findAllCategoriesByNameContainingIgnoreCase("", Sort.by(Sort.Direction.ASC, "name"));
//            else return this.categoryRepository.findAllCategoriesByNameContainingIgnoreCase("", Sort.by(Sort.Direction.DESC, "name"));
//        } else {
//            if (Objects.equals(sortName, "a-z")) return this.categoryRepository.findAllCategoriesByNameContainingIgnoreCase(q, Sort.by(Sort.Direction.ASC, "name"));
//            else return this.categoryRepository.findAllCategoriesByNameContainingIgnoreCase(q, Sort.by(Sort.Direction.DESC, "name"));
//        }

        if (page <= 0 || limit <= 0) throw new BadRequestException("Invalid pagination!");

        List<Sort.Order> sortByList = new ArrayList<>();
        for (String item : sort.split(","))
        {
            String[] srt = item.split(":");
            if (srt.length != 2)
                continue;
//                throw new BadRequestException("Invalid sorting!");

            String direction = srt[1].toLowerCase();
            String field = srt[0];

            sortByList.add(new Sort.Order(direction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, field));
        }

        Pageable pageable;
        if (isPage) pageable = PageRequest.of(page - 1, limit, Sort.by(sortByList));
        else pageable = Pageable.unpaged();

//        Map<String, String> search = new HashMap<>();
//        search.put("name", q);
////        search.put("description", q);
//        search.put("id", q);

//        if (q == null || q.equals("")) {
//            return this.categoryRepository.findAllCategoriesByNameContainingIgnoreCase(pageable, "");
//        } else {
//            return this.categoryRepository.findAllCategoriesByNameContainingIgnoreCase(pageable, q);

            /*
            return this.categoryRepository.findAll((Specification<CategoryEntity>) (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();

//                for (Map.Entry<String, String> entry : search.entrySet()) {
//                    Predicate predicate = criteriaBuilder.like(criteriaBuilder.upper(root.get(entry.getKey()).as(String.class)), "%" + entry.getValue().toUpperCase() + "%");
//                    predicates.add(predicate);
//                }

                for (Map.Entry<String, String> entry : reqParam.entrySet()) {
                    if (entry.getKey().toLowerCase().startsWith("q_")) {
                        Predicate predicate = criteriaBuilder.like(criteriaBuilder.upper(root.get(entry.getKey().split("q_", 2)[1]).as(String.class)), "%" + entry.getValue().toUpperCase() + "%");
                        predicates.add(predicate);
                    }
                }

                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            }, pageable);
             */

            return this.categoryRepository.findAll((Specification<CategoryEntity>) (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                for (Map.Entry<String, String> entry : reqParam.entrySet()) {
                    if (entry.getKey().startsWith("q_")) {
                        String qKey = entry.getKey().split("q_", 2)[1];
                        String qValue = entry.getValue() == null ? "" : entry.getValue();
                        predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get(qKey).as(String.class)), "%" + qValue.toUpperCase() + "%"));
                    }
                }

                if (predicates.size() == 0) predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("name").as(String.class)), "%" + "" + "%"));

                return criteriaBuilder.and(
                        isTrash ? criteriaBuilder.isNotNull(root.get("deletedAt")) : criteriaBuilder.isNull(root.get("deletedAt")), criteriaBuilder.or(predicates.toArray(Predicate[]::new))
                );
            }, pageable);
//        }
    }

    public CategoryEntity findOne(Long id) throws NotFoundException {
        return this.categoryRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(() -> new NotFoundException("Category not found"));
    }

    private CategoryEntity findOneWithSoftDeleted(Long id) throws NotFoundException {
        return this.categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
    }

    public CategoryEntity restore(Long id, RestoreCategoryRequest req) throws Exception {
        // get category data from db by id
        CategoryEntity category = this.findOneWithSoftDeleted(id);

        // check name from request exists or not in db
        if (this.categoryRepository.existsByNameAndDeletedAtIsNull(req.getName()))
            throw new AlreadyExistException("Category name already exists!");

        // move deleted_at field to null value
        category.setDeletedAt(null);
        category.setName(req.getName());

        try {
            return this.categoryRepository.save(category);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public CategoryEntity delete(Long id) throws Exception {
        // get category data from db by id
        CategoryEntity category = this.findOne(id);

        try {
            // if everything work well then delete
//            this.categoryRepository.deleteById(category.getId());
            category.setDeletedAt(new Date());
            this.categoryRepository.save(category);
        } catch (Exception ex) {
            throw new Exception(ex);
        }

        // return data
        return category;
    }
}
