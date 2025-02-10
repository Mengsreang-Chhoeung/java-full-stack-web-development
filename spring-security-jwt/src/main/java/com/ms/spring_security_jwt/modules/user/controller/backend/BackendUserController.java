package com.ms.spring_security_jwt.modules.user.controller.backend;

import com.ms.spring_security_jwt.constant.ApiDocsConstant;
import com.ms.spring_security_jwt.constant.AppConstant;
import com.ms.spring_security_jwt.constant.ResponseMessageConstant;
import com.ms.spring_security_jwt.constant.RestURIConstant;
import com.ms.spring_security_jwt.infrastructure.annotation.auth.RequireAdminOrSuperAdminRole;
import com.ms.spring_security_jwt.infrastructure.annotation.auth.RequireAuthenticatedUser;
import com.ms.spring_security_jwt.infrastructure.annotation.swagger.ApiBearerAuth;
import com.ms.spring_security_jwt.infrastructure.annotation.swagger.ApiDocErrorResponse;
import com.ms.spring_security_jwt.infrastructure.annotation.swagger.ApiDocSuccessResponse;
import com.ms.spring_security_jwt.infrastructure.annotation.swagger.ApiPagination;
import com.ms.spring_security_jwt.infrastructure.exception.NotFoundException;
import com.ms.spring_security_jwt.infrastructure.model.response.BaseResponse;
import com.ms.spring_security_jwt.infrastructure.model.response.body.BaseBodyResponse;
import com.ms.spring_security_jwt.infrastructure.service.UrlParamService;
import com.ms.spring_security_jwt.modules.user.model.entity.UserEntity;
import com.ms.spring_security_jwt.modules.user.model.request.BackendCreateUserRequest;
import com.ms.spring_security_jwt.modules.user.model.request.BackendUpdateUserRequest;
import com.ms.spring_security_jwt.modules.user.model.response.UserResponse;
import com.ms.spring_security_jwt.modules.user.service.UserService;
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

@RequireAuthenticatedUser
@RequireAdminOrSuperAdminRole
@ApiBearerAuth
@Tag(name = ApiDocsConstant.BACKEND_USER, description = ApiDocsConstant.BACKEND_USER_DESCRIPTION)
@ApiDocErrorResponse
@RestController
@RequestMapping(value = RestURIConstant.B_USER)
public class BackendUserController {
    private final UserService userService;

    @Autowired
    public BackendUserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "For administrator create a user")
    @ApiDocSuccessResponse
    @PostMapping("/create")
    public ResponseEntity<BaseBodyResponse> create(@Valid @RequestBody BackendCreateUserRequest backendCreateUserRequest) {
        this.userService.create(backendCreateUserRequest);

        return BaseBodyResponse.success(ResponseMessageConstant.CREATE_SUCCESSFULLY);
    }

    @Operation(summary = "For administrator update a user")
    @ApiDocSuccessResponse
    @PutMapping("/update/{id}")
    public ResponseEntity<BaseBodyResponse> update(@PathVariable Long id, @Valid @RequestBody BackendUpdateUserRequest backendUpdateUserRequest) {
        this.userService.update(id, backendUpdateUserRequest);

        return BaseBodyResponse.success(ResponseMessageConstant.UPDATE_SUCCESSFULLY);
    }

    @Operation(summary = "For administrator delete a user")
    @ApiDocSuccessResponse
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseBodyResponse> delete(@PathVariable Long id) {
        this.userService.softDelete(id);

        return BaseBodyResponse.success(ResponseMessageConstant.DELETE_SUCCESSFULLY);
    }

    @Operation(summary = "For administrator find a user")
    @ApiResponse(responseCode = "200", description = ResponseMessageConstant.OPERATION_SUCCESSFULLY, content = {
            @Content(mediaType = AppConstant.APPLICATION_JSON, schema = @Schema(implementation = UserResponse.class))
    })
    @GetMapping("/one/{id}")
    public ResponseEntity<BaseBodyResponse> findOne(@PathVariable Long id) {
        UserEntity user = this.userService.findOneAvailable(id).orElseThrow(() -> new NotFoundException("Not found!"));

        return BaseBodyResponse.success(UserResponse.toResponse(user), ResponseMessageConstant.FIND_ONE_SUCCESSFULLY);
    }

    @Operation(summary = "For administrator find all users")
    @ApiResponse(responseCode = "200", description = ResponseMessageConstant.OPERATION_SUCCESSFULLY, content = {
            @Content(mediaType = AppConstant.APPLICATION_JSON, schema = @Schema(implementation = UserResponse.class))
    })
    @ApiPagination
    @GetMapping("/all")
    public ResponseEntity<BaseBodyResponse> findAll(@Schema(hidden = true) UrlParamService urlParamService) {
        Page<UserEntity> userEntityPage = this.userService.findAllAvailable(urlParamService);
        Page<BaseResponse> userResponsePage = userEntityPage.map(UserResponse::toResponse);

        return BaseBodyResponse.pageSuccess(userResponsePage, ResponseMessageConstant.FIND_ALL_SUCCESSFULLY);
    }
}
