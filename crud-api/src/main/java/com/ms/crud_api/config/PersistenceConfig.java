package com.ms.crud_api.config;

import com.ms.crud_api.infrastructure.repository.implementation.BaseRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class, basePackages = {"com.ms.crud_api.infrastructure.repository", "com.ms.crud_api.repository"})
public class PersistenceConfig {
    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }

    private static class AuditorAwareImpl implements AuditorAware<String> {
        @Override
        public Optional<String> getCurrentAuditor() {
            return Optional.of("anonymousUser");
        }
    }
}
