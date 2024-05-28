package com.ms.spring_file_upload.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class AppProperties {
    @Value("${ms.app.api-url}")
    private String apiUrl;

    public String getApiUrl() {
        return apiUrl;
    }
}
