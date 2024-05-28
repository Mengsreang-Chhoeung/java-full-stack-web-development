package com.ms.spring_file_upload.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.ms.spring_file_upload.repository")
@EnableTransactionManagement
@EnableJpaAuditing
public class PersistenceConfig {
}
