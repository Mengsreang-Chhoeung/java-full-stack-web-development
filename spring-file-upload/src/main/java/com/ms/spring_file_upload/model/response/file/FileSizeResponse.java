package com.ms.spring_file_upload.model.response.file;

public class FileSizeResponse {
    private Long originalValue;
    private Long formatValue;
    private String formatType;
    private String normalized;

    public Long getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(Long originalValue) {
        this.originalValue = originalValue;
    }

    public Long getFormatValue() {
        return formatValue;
    }

    public void setFormatValue(Long formatValue) {
        this.formatValue = formatValue;
    }

    public String getFormatType() {
        return formatType;
    }

    public void setFormatType(String formatType) {
        this.formatType = formatType;
    }

    public String getNormalized() {
        return normalized;
    }

    public void setNormalized(String normalized) {
        this.normalized = normalized;
    }
}
