package roomescape.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class AuthenticationConfig implements WebMvcConfigurer {

    private final AuthenticationPrincipalArgumentResolver argumentResolver;

    public AuthenticationConfig(AuthenticationPrincipalArgumentResolver argumentResolver) {
        this.argumentResolver = argumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(argumentResolver);
    }
}
