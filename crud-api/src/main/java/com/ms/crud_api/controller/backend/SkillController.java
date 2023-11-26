package com.ms.crud_api.controller.backend;

import com.ms.crud_api.exception.BadRequestException;
import com.ms.crud_api.exception.NotFoundException;
import com.ms.crud_api.infrastructure.model.response.BaseResponse;
import com.ms.crud_api.infrastructure.model.response.body.BaseBodyResponse;
import com.ms.crud_api.model.entity.SkillEntity;
import com.ms.crud_api.model.request.skill.RestoreSkillRequest;
import com.ms.crud_api.model.request.skill.SkillRequest;
import com.ms.crud_api.model.response.error.ErrorResponse;
import com.ms.crud_api.model.response.skill.SkillResponse;
import com.ms.crud_api.service.SkillService;
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

@Tag(name = "Backend Skill Controller", description = "Controller for admin manage skill")
@RestController
@RequestMapping("/api/skill")
public class SkillController {
    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @Operation(summary = "Endpoint for admin create a skill", description = "Admin can creating a skill by using this endpoint", responses = {
            @ApiResponse(description = "Success", responseCode = "201", content = @Content(schema = @Schema(implementation = SkillResponse.class), mediaType = "application/json")),
            @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))
    })
    @PostMapping
    public ResponseEntity<BaseBodyResponse> create(@Valid @RequestBody SkillRequest request) throws Exception {
        SkillEntity skill = this.skillService.create(request);

        return BaseBodyResponse.createSuccess(SkillResponse.fromEntity(skill), "Created Successfully");
    }

    @Operation(summary = "Endpoint for admin update a skill", description = "Admin can updating a skill by using this endpoint", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = SkillResponse.class), mediaType = "application/json")),
            @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))
    })
    @PutMapping("/{id}")
    public ResponseEntity<BaseBodyResponse> update(@PathVariable Long id, @Valid @RequestBody SkillRequest request) throws Exception {
        SkillEntity skill = this.skillService.update(id, request);

        return BaseBodyResponse.success(SkillResponse.fromEntity(skill), "Updated Successfully");
    }

    @Operation(summary = "Endpoint for admin restore a skill", description = "Admin can restoring a skill by using this endpoint", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = SkillResponse.class), mediaType = "application/json")),
            @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))
    })
    @PutMapping("/restore/{id}")
    public ResponseEntity<BaseBodyResponse> restore(@PathVariable Long id, @Valid @RequestBody RestoreSkillRequest req) throws Exception {
        SkillEntity skill = this.skillService.restore(id, req);

        return BaseBodyResponse.success(SkillResponse.fromEntity(skill), "Restored Successfully");
    }

    @Operation(summary = "Endpoint for admin find all skills", description = "Admin can finding all skills by using this endpoint", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = SkillResponse.class), mediaType = "application/json")),
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
        Page<BaseResponse> skill = this.skillService.findAll(page, limit, isPage, sort, isTrash, reqParam).map(SkillResponse::fromEntity);

        return BaseBodyResponse.success(skill, "Success");
    }

    @Operation(summary = "Endpoint for admin finding a skill", description = "Admin can finding a skill by using this endpoint", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = SkillResponse.class), mediaType = "application/json")),
            @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<BaseBodyResponse> findOne(@PathVariable Long id) throws NotFoundException {
        SkillEntity skill = this.skillService.findOne(id);

        return BaseBodyResponse.success(SkillResponse.fromEntity(skill), "Found Successfully");
    }

    @Operation(summary = "Endpoint for admin delete a skill", description = "Admin can deleting a skill by using this endpoint", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = SkillResponse.class), mediaType = "application/json")),
            @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseBodyResponse> delete(@PathVariable Long id) throws Exception {
        SkillEntity category = this.skillService.delete(id);

        return BaseBodyResponse.success(SkillResponse.fromEntity(category), "Deleted Successfully");
    }

    @GetMapping("/validate/{id}")
    public ResponseEntity<Boolean> validateId(@PathVariable Long id) {
        boolean validate = this.skillService.validateId(id);

        return ResponseEntity.ok(validate);
    }
}
