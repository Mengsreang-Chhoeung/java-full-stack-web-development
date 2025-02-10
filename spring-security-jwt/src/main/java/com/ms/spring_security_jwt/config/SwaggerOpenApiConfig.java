package com.ms.spring_security_jwt.config;

import com.ms.spring_security_jwt.constant.AppConstant;
import com.ms.spring_security_jwt.infrastructure.annotation.swagger.ApiBearerAuthConfig;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApiBearerAuthConfig
public class SwaggerOpenApiConfig {
    @Bean
    public GroupedOpenApi backendApiGroup() {
        return GroupedOpenApi.builder().group("backend-api").addOpenApiCustomizer(openApi -> openApi.info(this.getBackendApiInfo())).packagesToScan(this.getBackendPackagesToScan()).build();
    }

    @Bean
    public GroupedOpenApi frontendApiGroup() {
        return GroupedOpenApi.builder().group("frontend-api").addOpenApiCustomizer(openApi -> openApi.info(this.getFrontendApiInfo())).packagesToScan(this.getFrontendPackagesToScan()).build();
    }

    private String[] getBackendPackagesToScan() {
        return new String[]{
                "com.ms.spring_security_jwt.modules.user.controller.backend",
                "com.ms.spring_security_jwt.modules.auth.controller.backend"
        };
    }

    private String[] getFrontendPackagesToScan() {
        return new String[]{"com.ms.spring_security_jwt.modules.auth.controller.frontend"};
    }

    private Info getBackendApiInfo() {
        Contact contact = new Contact();
        contact.setName("Mengsreang-Chhoeung");
        contact.setEmail("chhoeungmengsreang789@gmail.com");
        contact.setUrl("https://github.com/Mengsreang-Chhoeung");

        return new Info().title("Spring Security JWT Backend API").description(AppConstant.APP_DESCRIPTION).contact(contact).version(AppConstant.APP_VERSION);
    }

    private Info getFrontendApiInfo() {
        Contact contact = new Contact();
        contact.setName("Mengsreang-Chhoeung");
        contact.setEmail("chhoeungmengsreang789@gmail.com");
        contact.setUrl("https://github.com/Mengsreang-Chhoeung");

        return new Info().title("Spring Security JWT Frontend API").description(AppConstant.APP_DESCRIPTION).contact(contact).version(AppConstant.APP_VERSION);
    }
}
