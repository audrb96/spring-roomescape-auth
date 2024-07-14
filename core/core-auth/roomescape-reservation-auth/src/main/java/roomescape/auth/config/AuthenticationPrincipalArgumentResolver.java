package roomescape.auth.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import roomescape.auth.annotation.AuthenticationPrincipal;
import roomescape.jwt.JwtToken;
import roomescape.jwt.component.decoder.JwtTokenDecoder;
import roomescape.jwt.component.extractor.CookieTokenExtractor;

import java.util.Objects;

@Component
public class AuthenticationPrincipalArgumentResolver implements HandlerMethodArgumentResolver {

    private final CookieTokenExtractor tokenExtractor;
    private final JwtTokenDecoder decoder;

    public AuthenticationPrincipalArgumentResolver(CookieTokenExtractor tokenExtractor, JwtTokenDecoder decoder) {
        this.tokenExtractor = tokenExtractor;
        this.decoder = decoder;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticationPrincipal.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        JwtToken token = tokenExtractor.extract(Objects.requireNonNull(webRequest.getNativeRequest(HttpServletRequest.class)));
        return decoder.decode(token);
    }
}
