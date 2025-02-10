package com.ms.spring_security_jwt.modules.auth.controller.backend;

import com.ms.spring_security_jwt.constant.ApiDocsConstant;
import com.ms.spring_security_jwt.constant.AppConstant;
import com.ms.spring_security_jwt.constant.ResponseMessageConstant;
import com.ms.spring_security_jwt.constant.RestURIConstant;
import com.ms.spring_security_jwt.infrastructure.annotation.swagger.ApiBearerAuth;
import com.ms.spring_security_jwt.infrastructure.annotation.swagger.ApiDocErrorResponse;
import com.ms.spring_security_jwt.infrastructure.annotation.auth.RequireAdminOrSuperAdminRole;
import com.ms.spring_security_jwt.infrastructure.annotation.auth.RequireAuthenticatedUser;
import com.ms.spring_security_jwt.infrastructure.model.response.body.BaseBodyResponse;
import com.ms.spring_security_jwt.modules.auth.model.request.BackendAuthLoginRequest;
import com.ms.spring_security_jwt.modules.auth.model.response.AuthTokenResponse;
import com.ms.spring_security_jwt.modules.auth.service.AuthService;
import com.ms.spring_security_jwt.modules.user.model.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = ApiDocsConstant.BACKEND_AUTH, description = ApiDocsConstant.BACKEND_AUTH_DESCRIPTION)
@ApiDocErrorResponse
@RestController
@RequestMapping(value = RestURIConstant.B_AUTH)
public class BackendAuthController {
    private final AuthService authService;

    @Autowired
    public BackendAuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "For administrator login a user")
    @ApiResponse(responseCode = "200", description = ResponseMessageConstant.OPERATION_SUCCESSFULLY, content = {
            @Content(mediaType = AppConstant.APPLICATION_JSON, schema = @Schema(implementation = AuthTokenResponse.class))
    })
    @PostMapping("/login")
    public ResponseEntity<BaseBodyResponse> login(@Valid @RequestBody BackendAuthLoginRequest request) {
        AuthTokenResponse data = this.authService.backendLogin(request);

        return BaseBodyResponse.success(data, ResponseMessageConstant.LOGIN_SUCCESS);
    }

    @RequireAuthenticatedUser
    @RequireAdminOrSuperAdminRole
    @ApiBearerAuth
    @Operation(summary = "For administrator get own info")
    @ApiResponse(responseCode = "200", description = ResponseMessageConstant.OPERATION_SUCCESSFULLY, content = {
            @Content(mediaType = AppConstant.APPLICATION_JSON, schema = @Schema(implementation = UserResponse.class))
    })
    @GetMapping("/own-info")
    public ResponseEntity<BaseBodyResponse> ownInfo() {
        UserResponse data = this.authService.ownInfo();

        return BaseBodyResponse.success(data, ResponseMessageConstant.FETCH_ME_SUCCESS);
    }
}
