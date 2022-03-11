package com.changsu.project.changsushop.jpa;

import com.changsu.project.changsushop.auth.security.CustomDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null == authentication || !authentication.isAuthenticated()) {
				return null;
		}
        CustomDetails userDetails = (CustomDetails) authentication.getPrincipal();
        Optional<String> name = Optional.of(userDetails.getMember().getName());
        return name;
    }
}
