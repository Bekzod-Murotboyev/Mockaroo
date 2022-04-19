package uz.pdp.mockaroo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.pdp.mockaroo.entity.User;

import java.util.Optional;

@Configuration
public class AuditingConfig {

    @Bean
    public AuditorAware<Long> auditorAware() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal()))
            return () -> Optional.of(((User) auth.getPrincipal()).getId());
        return Optional::empty;
    }

}
