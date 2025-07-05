package com.ms.learn.java.records.sb.modules.category.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ms.learn.java.records.sb.exception.BadRequestException;
import com.ms.learn.java.records.sb.exception.ConflictException;
import com.ms.learn.java.records.sb.exception.NotFoundException;
import com.ms.learn.java.records.sb.modules.category.entity.model.CategoryEntity;
import com.ms.learn.java.records.sb.modules.category.entity.request.CategoryRequest;
import com.ms.learn.java.records.sb.modules.category.entity.response.CategoryResponse;

@Service
public class CategoryService {
    private List<CategoryEntity> categoryEntities = new ArrayList<>();
    private Long categoryIdAutoIncrement = 0L;

    private void setCategoryIdAutoIncrement() {
        this.categoryIdAutoIncrement = this.categoryIdAutoIncrement + 1;
    }

    public CategoryResponse create(CategoryRequest request) {
        // validate the request
        if (request.name().isBlank() || request.name() == null)
            throw new BadRequestException("name is required.");

        // validate the name exist or not
        CategoryEntity foundNameExist = this.categoryEntities.stream()
                .filter(category -> category.getName().equals(request.name()))
                .findFirst()
                .orElse(null);
        if (foundNameExist != null) {
            throw new ConflictException("Category name already exists.");
        }

        // set auto increment to id
        this.setCategoryIdAutoIncrement();

        // convert the request model to the entity model
        CategoryEntity categoryEntity = request.toEntity();
        categoryEntity.setId(this.categoryIdAutoIncrement);

        // saving the data
        categoryEntities.add(categoryEntity);

        // response the data
        return CategoryResponse.toResponse(categoryEntity);
    }

    public CategoryResponse update(Long id, CategoryRequest request) {
        // Find the category to update
        CategoryEntity found = this.categoryEntities.stream()
                .filter(category -> category.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Category not found."));

        // Update name if provided and not blank
        if (request.name() != null && !request.name().isBlank()) {
            // Check for duplicate name (other than current)
            boolean duplicate = this.categoryEntities.stream()
                    .anyMatch(category -> !category.getId().equals(id) && category.getName().equals(request.name()));
            if (duplicate) {
                throw new ConflictException("Category name already exists.");
            }
            found.setName(request.name());
        }

        // Update description if provided (even if blank)
        if (request.description() != null) {
            found.setDescription(request.description());
        }

        return CategoryResponse.toResponse(found);
    }

    public CategoryResponse delete(Long id) {
        Iterator<CategoryEntity> iterator = this.categoryEntities.iterator();
        while (iterator.hasNext()) {
            CategoryEntity category = iterator.next();
            if (category.getId().equals(id)) {
                iterator.remove();
                return CategoryResponse.toResponse(category);
            }
        }
        throw new NotFoundException("Category not found.");
    }

    public CategoryResponse findOne(Long id) {
        CategoryEntity found = this.categoryEntities.stream()
                .filter(category -> category.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Category not found."));

        return CategoryResponse.toResponse(found);
    }

    public List<CategoryResponse> findAll() {
        return this.categoryEntities.stream()
                .map(CategoryResponse::toResponse)
                .collect(Collectors.toList());
    }
}
