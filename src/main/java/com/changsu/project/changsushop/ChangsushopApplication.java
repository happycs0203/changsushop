package com.changsu.project.changsushop;

import com.changsu.project.changsushop.domain.Member;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class ChangsushopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChangsushopApplication.class, args);
	}

//	@Bean
//	public AuditorAware<String> auditorProvider(){
//
//		return () -> {
//			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//			if (null == authentication || !authentication.isAuthenticated()) {
//				return null;
//			}
//			Member member = (Member) authentication.getPrincipal();
//			return Optional.of(member.getName());
//		};
//	}

}
