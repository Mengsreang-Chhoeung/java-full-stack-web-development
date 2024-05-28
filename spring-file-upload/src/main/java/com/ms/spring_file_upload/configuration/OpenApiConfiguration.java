package com.ms.spring_file_upload.configuration;

import io.swagger.v3.oas.models.info.*;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public GroupedOpenApi frontendGroup() {
        return GroupedOpenApi
                .builder()
                .group("frontend-api")
                .addOpenApiCustomizer(openApi -> openApi
                        .info(getFrontendApiInfo())
                )
                .packagesToScan("com.ms.spring_file_upload.controller.frontend")
                .build();
    }

    private Info getFrontendApiInfo() {
        Contact contact = new Contact();
        contact.setName("Mengsreang-Chhhoeung");
        contact.setEmail("chhoeungmengsreang789@gmail.com");
        contact.setUrl("https://github.com/Mengsreang-Chhoeung");

        return new Info().title("File Upload API").description("Frontend File Upload API").contact(contact).version("1.0.0");
    }
}
