package com.ms.learn.java.records.sb.modules.category.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.learn.java.records.sb.modules.category.entity.request.CategoryRequest;
import com.ms.learn.java.records.sb.modules.category.entity.response.CategoryResponse;
import com.ms.learn.java.records.sb.modules.category.service.CategoryService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public List<CategoryResponse> findAll() {
        return this.categoryService.findAll();
    }

    @GetMapping("/one/{id}")
    public CategoryResponse findOne(@PathVariable Long id) {
        return this.categoryService.findOne(id);
    }

    @PostMapping("/create")
    public CategoryResponse create(@RequestBody CategoryRequest request) {
        return this.categoryService.create(request);
    }

    @PutMapping("update/{id}")
    public CategoryResponse update(@PathVariable Long id, @RequestBody CategoryRequest request) {
        return this.categoryService.update(id, request);
    }

    @DeleteMapping("delete/{id}")
    public CategoryResponse delete(@PathVariable Long id) {
        return this.categoryService.delete(id);
    }

}
