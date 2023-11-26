package com.ms.crud_api.controller.backend;

import com.ms.crud_api.exception.BadRequestException;
import com.ms.crud_api.exception.NotFoundException;
import com.ms.crud_api.infrastructure.model.response.BaseResponse;
import com.ms.crud_api.infrastructure.model.response.body.BaseBodyResponse;
import com.ms.crud_api.model.entity.CategoryEntity;
import com.ms.crud_api.model.request.category.CategoryRequest;
import com.ms.crud_api.model.request.category.RestoreCategoryRequest;
import com.ms.crud_api.model.response.category.CategoryResponse;
import com.ms.crud_api.service.CategoryService;
import com.ms.crud_api.model.response.error.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@Tag(name = "Backend Category Controller", description = "Controller for admin manage category")
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Endpoint for admin create a category", description = "Admin can creating a category by using this endpoint", responses = {
            @ApiResponse(description = "Success", responseCode = "201", content = @Content(schema = @Schema(implementation = CategoryResponse.class), mediaType = "application/json")),
            @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))
    })
    @PostMapping
    public ResponseEntity<BaseBodyResponse> create(@Valid @RequestBody CategoryRequest request) throws Exception {
        CategoryEntity category = this.categoryService.create(request);

        return BaseBodyResponse.createSuccess(CategoryResponse.fromEntity(category), "Created Successfully");
    }

    @Operation(summary = "Endpoint for admin update a category", description = "Admin can updating a category by using this endpoint", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = CategoryResponse.class), mediaType = "application/json")),
            @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))
    })
    @PutMapping("/{id}")
    public ResponseEntity<BaseBodyResponse> update(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) throws Exception {
        CategoryEntity category = this.categoryService.update(id, request);

        return BaseBodyResponse.success(CategoryResponse.fromEntity(category), "Updated Successfully");
    }

    @Operation(summary = "Endpoint for admin restore a category", description = "Admin can restoring a category by using this endpoint", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = CategoryResponse.class), mediaType = "application/json")),
            @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))
    })
    @PutMapping("/restore/{id}")
    public ResponseEntity<BaseBodyResponse> restore(@PathVariable Long id, @Valid @RequestBody RestoreCategoryRequest req) throws Exception {
        CategoryEntity category = this.categoryService.restore(id, req);

        return BaseBodyResponse.success(CategoryResponse.fromEntity(category), "Restored Successfully");
    }

    @Operation(summary = "Endpoint for admin find all categories", description = "Admin can finding all categories by using this endpoint", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = CategoryResponse.class), mediaType = "application/json")),
            @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))
    })
    @GetMapping
    public ResponseEntity<BaseBodyResponse> findAll(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "isPage", required = false, defaultValue = "true") boolean isPage,
            @RequestParam(name = "sort", required = false, defaultValue = "id:desc") String sort,
            @RequestParam(name = "isTrash", required = false, defaultValue = "false") boolean isTrash,
            @RequestParam Map<String, String> reqParam
    ) throws BadRequestException {
//        List<CategoryResponse> category = this.categoryService.findAll(q, page, limit, Objects.equals(isPage, "true"), sort, reqParam).stream().map(CategoryResponse::fromEntity).toList();

        Page<BaseResponse> category = this.categoryService.findAll(page, limit, isPage, sort, isTrash, reqParam).map(CategoryResponse::fromEntity);

        return BaseBodyResponse.success(category, "Success");
    }

    @Operation(summary = "Endpoint for admin finding a category", description = "Admin can finding a category by using this endpoint", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = CategoryResponse.class), mediaType = "application/json")),
            @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<BaseBodyResponse> findOne(@PathVariable Long id) throws NotFoundException {
        CategoryEntity category = this.categoryService.findOne(id);

        return BaseBodyResponse.success(CategoryResponse.fromEntity(category), "Found Successfully");
    }

    @Operation(summary = "Endpoint for admin delete a category", description = "Admin can deleting a category by using this endpoint", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = CategoryResponse.class), mediaType = "application/json")),
            @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseBodyResponse> delete(@PathVariable Long id) throws Exception {
        CategoryEntity category = this.categoryService.delete(id);

        return BaseBodyResponse.success(CategoryResponse.fromEntity(category), "Deleted Successfully");
    }
}
