package com.changsu.project.changsushop.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @desc 공통으로 들어가는 BaseEntity, BaseTimeEntity 사용할 수 있게 매핑
 * Spring Audit 기능
 * @author ChangSu, Ham
 * @version 1.0
 */
@Configuration
@EnableJpaAuditing
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
