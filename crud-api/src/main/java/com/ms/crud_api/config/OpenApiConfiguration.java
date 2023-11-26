package com.ms.crud_api.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public GroupedOpenApi backendGroup() {
        return GroupedOpenApi.builder().group("backend-api").addOpenApiCustomizer(openApi -> openApi.info(getBackendApiInfo())).packagesToScan("com.ms.crud_api.controller.backend").build();
    }

    @Bean
    public GroupedOpenApi frontendGroup() {
        return GroupedOpenApi.builder().group("frontend-api").addOpenApiCustomizer(openApi -> openApi.info(getFrontendApiInfo())).packagesToScan("com.ms.crud_api.controller.frontend").build();
    }

    private Info getBackendApiInfo() {
        Contact contact = new Contact();
        contact.setName("Mengsreang-Chhhoeung");
        contact.setEmail("chhoeungmengsreang789@gmail.com");
        contact.setUrl("https://github.com/Mengsreang-Chhoeung");

        return new Info().title("Ecommerce API").description("Backend Ecommerce API").contact(contact).version("1.0.1");
    }

    private Info getFrontendApiInfo() {
        Contact contact = new Contact();
        contact.setName("Mengsreang-Chhhoeung");
        contact.setEmail("chhoeungmengsreang789@gmail.com");
        contact.setUrl("https://github.com/Mengsreang-Chhoeung");

        return new Info().title("Ecommerce API").description("Frontend Ecommerce API").contact(contact).version("1.0.1");
    }
}
