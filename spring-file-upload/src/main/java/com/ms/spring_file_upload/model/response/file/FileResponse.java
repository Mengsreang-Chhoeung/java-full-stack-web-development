package com.ms.spring_file_upload.model.response.file;

import com.ms.spring_file_upload.infrastructure.model.response.BaseResponse;
import com.ms.spring_file_upload.model.entity.FileEntity;
import com.ms.spring_file_upload.property.AppProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FileResponse implements BaseResponse {
    private Long id;
    private Date createdAt;
    private String name;
    private String originalName;
    private String url;
    private FileSizeResponse size;
    private String type;
    private Boolean isTrash;

    public static FileResponse toResponse(FileEntity entity, AppProperties appProperties) {
        if (entity == null) return null;

        FileResponse response = new FileResponse();
        response.setId(entity.getId());
        response.setCreatedAt(entity.getCreatedAt());
        response.setName(entity.getName());
        response.setOriginalName(entity.getOriginalName());
        response.setUrl(appProperties.getApiUrl() + "/file/load/" + entity.getName());

        FileSizeResponse fileSizeResponse = new FileSizeResponse();
        Long fileSizeInKb = entity.getSize() / 1024;
        String fileFormatType = "KB";

        fileSizeResponse.setOriginalValue(entity.getSize());
        fileSizeResponse.setFormatValue(fileSizeInKb);
        fileSizeResponse.setFormatType(fileFormatType);
        fileSizeResponse.setNormalized(fileSizeInKb + " " + fileFormatType);

        response.setSize(fileSizeResponse);
        response.setType(entity.getType());
        response.setIsTrash(entity.getDeletedAt() != null);

        return response;
    }

    public static List<FileResponse> toResponse(List<FileEntity> entities, AppProperties appProperties) {
        if (entities.size() == 0) return Collections.emptyList();

        List<FileResponse> fileResponses = new ArrayList<>();
        for (FileEntity file: entities) {
            fileResponses.add(toResponse(file, appProperties));
        }

        return fileResponses;
    }

    public Long getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getUrl() {
        return url;
    }

    public FileSizeResponse getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSize(FileSizeResponse size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsTrash() {
        return this.isTrash;
    }

    public void setIsTrash(Boolean isTrash) {
        this.isTrash = isTrash;
    }
}
