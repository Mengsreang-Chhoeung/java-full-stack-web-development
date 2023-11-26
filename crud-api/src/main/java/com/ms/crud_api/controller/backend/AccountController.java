package com.ms.crud_api.controller.backend;

import com.ms.crud_api.exception.BadRequestException;
import com.ms.crud_api.exception.NotFoundException;
import com.ms.crud_api.infrastructure.model.response.BaseResponse;
import com.ms.crud_api.infrastructure.model.response.body.BaseBodyResponse;
import com.ms.crud_api.model.entity.AccountEntity;
import com.ms.crud_api.model.request.account.AccountRequest;
import com.ms.crud_api.model.request.account.RechargeAccountBalanceRequest;
import com.ms.crud_api.model.request.account.RestoreAccountRequest;
import com.ms.crud_api.model.response.account.AccountResponse;
import com.ms.crud_api.model.response.error.ErrorResponse;
import com.ms.crud_api.service.AccountService;
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

@Tag(name = "Backend Account Controller", description = "Controller for admin manage account")
@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(summary = "Endpoint for admin create a account", description = "Admin can creating a account by using this endpoint", responses = {@ApiResponse(description = "Success", responseCode = "201", content = @Content(schema = @Schema(implementation = AccountResponse.class), mediaType = "application/json")), @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))})
    @PostMapping
    public ResponseEntity<BaseBodyResponse> create(@Valid @RequestBody AccountRequest request) throws Exception {
        AccountEntity account = this.accountService.create(request);

        return BaseBodyResponse.createSuccess(AccountResponse.fromEntity(account), "Created Successfully");
    }

    @Operation(summary = "Endpoint for admin update a account", description = "Admin can updating a account by using this endpoint", responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = AccountResponse.class), mediaType = "application/json")), @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))})
    @PutMapping("/{id}")
    public ResponseEntity<BaseBodyResponse> update(@PathVariable Long id, @Valid @RequestBody AccountRequest request) throws Exception {
        AccountEntity account = this.accountService.update(id, request);

        return BaseBodyResponse.success(AccountResponse.fromEntity(account), "Updated Successfully");
    }

    @Operation(summary = "Endpoint for admin restore a account", description = "Admin can restoring a account by using this endpoint", responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = AccountResponse.class), mediaType = "application/json")), @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))})
    @PutMapping("/restore/{id}")
    public ResponseEntity<BaseBodyResponse> restore(@PathVariable Long id, @Valid @RequestBody RestoreAccountRequest req) throws Exception {
        AccountEntity account = this.accountService.restore(id, req);

        return BaseBodyResponse.success(AccountResponse.fromEntity(account), "Restored Successfully");
    }

    @Operation(summary = "Endpoint for admin find all accounts", description = "Admin can finding all accounts by using this endpoint", responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = AccountResponse.class), mediaType = "application/json")), @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))})
    @GetMapping
    public ResponseEntity<BaseBodyResponse> findAll(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "limit", defaultValue = "10") int limit, @RequestParam(name = "isPage", required = false, defaultValue = "true") boolean isPage, @RequestParam(name = "sort", required = false, defaultValue = "id:desc") String sort, @RequestParam(name = "isTrash", required = false, defaultValue = "false") boolean isTrash, @RequestParam Map<String, String> reqParam) throws BadRequestException {
        Page<BaseResponse> account = this.accountService.findAll(page, limit, isPage, sort, isTrash, reqParam).map(AccountResponse::fromEntity);

        return BaseBodyResponse.success(account, "Success");
    }

    @Operation(summary = "Endpoint for admin finding a account", description = "Admin can finding a account by using this endpoint", responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = AccountResponse.class), mediaType = "application/json")), @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))})
    @GetMapping("/{id}")
    public ResponseEntity<BaseBodyResponse> findOne(@PathVariable Long id) throws NotFoundException {
        AccountEntity account = this.accountService.findOne(id);

        return BaseBodyResponse.success(AccountResponse.fromEntity(account), "Found Successfully");
    }

    @Operation(summary = "Endpoint for admin delete a account", description = "Admin can deleting a account by using this endpoint", responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = AccountResponse.class), mediaType = "application/json")), @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))})
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseBodyResponse> delete(@PathVariable Long id) throws Exception {
        AccountEntity account = this.accountService.delete(id);

        return BaseBodyResponse.success(AccountResponse.fromEntity(account), "Deleted Successfully");
    }

    @Operation(summary = "Endpoint for admin recharge a account balance", description = "Admin can recharge a account balance by using this endpoint", responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = AccountResponse.class), mediaType = "application/json")), @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))})
    @PutMapping("/recharge-balance/{id}")
    public ResponseEntity<BaseBodyResponse> rechargeBalance(@PathVariable Long id, @RequestBody RechargeAccountBalanceRequest request) throws Exception {
        AccountEntity account = this.accountService.rechargeBalance(id, request);

        return BaseBodyResponse.success(AccountResponse.fromEntity(account), "Recharge Balance Successfully");
    }

    @Operation(summary = "Endpoint for admin get report", description = "Admin can getting a report by using this endpoint", responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = AccountResponse.class), mediaType = "application/json")), @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))})
    @GetMapping("/get-report")
    public ResponseEntity<BaseBodyResponse> getReport() throws Exception {
        this.accountService.getReports();

        return BaseBodyResponse.success(AccountResponse.fromEntity(new AccountEntity()), "Getting Report Successfully");
    }
}
