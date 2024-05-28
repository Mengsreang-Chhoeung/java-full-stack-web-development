package com.ms.spring_file_upload.controller.frontend;

import com.ms.spring_file_upload.constant.RestURIConstant;
import com.ms.spring_file_upload.exception.BadRequestException;
import com.ms.spring_file_upload.infrastructure.model.body.BaseBodyResponse;
import com.ms.spring_file_upload.infrastructure.model.body.ErrorResponse;
import com.ms.spring_file_upload.model.entity.FileEntity;
import com.ms.spring_file_upload.model.request.file.UpdateFileNameRequest;
import com.ms.spring_file_upload.model.response.file.FileResponse;
import com.ms.spring_file_upload.property.AppProperties;
import com.ms.spring_file_upload.service.FileService;
import com.ms.spring_file_upload.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Frontend File Controller", description = "Controller for user manage file upload")
@RestController
@RequestMapping(value = RestURIConstant.FILE)
public class FileController {
    private final StorageService storageService;
    private final FileService fileService;
    private final AppProperties appProperties;

    @Autowired
    public FileController(StorageService storageService, FileService fileService, AppProperties appProperties) {
        this.storageService = storageService;
        this.fileService = fileService;
        this.appProperties = appProperties;
    }

    @Operation(summary = "Endpoint for user upload a file", description = "User can uploading a file by using this endpoint", responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = FileResponse.class), mediaType = "application/json")), @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))})
    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<BaseBodyResponse> upload(@RequestPart MultipartFile file) {
        FileEntity data = this.fileService.upload(file);

        return BaseBodyResponse.success(FileResponse.toResponse(data, this.appProperties), null, "Upload Successfully");
    }

    @Operation(summary = "Endpoint for user upload files", description = "User can uploading files by using this endpoint", responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = FileResponse.class)), mediaType = "application/json")), @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))})
    @PostMapping(value = "/batch-upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<BaseBodyResponse> batchUpload(@RequestPart List<MultipartFile> files) {
        List<FileEntity> data = this.fileService.batchUpload(files);

        return BaseBodyResponse.success(FileResponse.toResponse(data, this.appProperties), null, "Batch Upload Successfully");
    }

    @Operation(summary = "Endpoint for user get all files", description = "User can getting all files by using this endpoint", responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = FileResponse.class)), mediaType = "application/json")), @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))})
    @GetMapping("/all")
    public ResponseEntity<BaseBodyResponse> getAll(
            @RequestParam(required = false, name = "page", defaultValue = "0") int page,
            @RequestParam(required = false, name = "size", defaultValue = "10") int pageSize,
            @RequestParam(required = false, name = "isTrash", defaultValue = "false") boolean isTrash,
            @RequestParam(required = false, name = "q", defaultValue = "") String q
    ) {
        Page<FileEntity> data = this.fileService.findAll(page, pageSize, isTrash, q);

        return BaseBodyResponse.success(FileResponse.toResponse(data.getContent(), this.appProperties), data, "Find all successfully");
    }

    @Operation(summary = "Endpoint for user restore a file", description = "User can restoring a file by using this endpoint", responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = FileResponse.class), mediaType = "application/json")), @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))})
    @PatchMapping("/restore/{id}")
    public ResponseEntity<BaseBodyResponse> restore(@PathVariable Long id) {
        FileEntity data = this.fileService.restore(id);

        return BaseBodyResponse.success(FileResponse.toResponse(data, this.appProperties), null, "Restore Successfully");
    }

    @Operation(summary = "Endpoint for user update a file name", description = "User can updating a file name by using this endpoint", responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = FileResponse.class), mediaType = "application/json")), @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))})
    @PatchMapping("/update-file-name/{id}")
    public ResponseEntity<BaseBodyResponse> updateFileName(@PathVariable Long id, @RequestBody UpdateFileNameRequest request) {
        FileEntity data = this.fileService.updateFileName(id, request);

        return BaseBodyResponse.success(FileResponse.toResponse(data, this.appProperties), null, "Update Successfully");
    }

    @Operation(summary = "Endpoint for user delete a file", description = "User can deleting a file by using this endpoint", responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = FileResponse.class), mediaType = "application/json")), @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseBodyResponse> delete(@PathVariable Long id) {
        FileEntity data = this.fileService.delete(id);

        return BaseBodyResponse.success(FileResponse.toResponse(data, this.appProperties), null, "Delete Successfully");
    }

    @Operation(summary = "Endpoint for user delete a file from trash", description = "User can deleting a file from trash by using this endpoint", responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = FileResponse.class), mediaType = "application/json")), @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))})
    @DeleteMapping("/delete-from-trash/{id}")
    public ResponseEntity<BaseBodyResponse> deleteFromTrash(@PathVariable Long id) {
        FileEntity data = this.fileService.deleteFromTrash(id);

        return BaseBodyResponse.success(FileResponse.toResponse(data, this.appProperties), null, "Delete from trash Successfully");
    }

    @Operation(summary = "Endpoint for user force delete a file", description = "User can force deleting a file by using this endpoint", responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = FileResponse.class), mediaType = "application/json")), @ApiResponse(description = "Error", responseCode = "400-500", content = @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json"))})
    @DeleteMapping("/force-delete/{id}")
    public ResponseEntity<BaseBodyResponse> forceDelete(@PathVariable Long id) {
        FileEntity data = this.fileService.forceDelete(id);

        return BaseBodyResponse.success(FileResponse.toResponse(data, this.appProperties), null, "Force Delete Successfully");
    }

    @GetMapping("/load/{filename}")
    public void loadFile(@PathVariable String filename, HttpServletResponse response) {
        this.storageService.loadFile(filename, response);
    }
}
