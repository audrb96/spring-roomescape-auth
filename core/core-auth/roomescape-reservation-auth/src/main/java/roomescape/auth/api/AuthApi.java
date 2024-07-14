package roomescape.auth.api;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import roomescape.auth.annotation.AuthenticationPrincipal;
import roomescape.auth.api.dto.request.LoginRequest;
import roomescape.auth.api.dto.response.LoginCheckResponse;
import roomescape.auth.service.AuthService;
import roomescape.auth.service.query.LoginCheckQuery;
import roomescape.domain.user.User;
import roomescape.domain.user.vo.UserId;
import roomescape.jwt.JwtToken;
import roomescape.jwt.component.extractor.CookieTokenExtractor;

@RestController
public class AuthApi {

    private final AuthService authService;
    private final CookieTokenExtractor tokenExtractor;

    public AuthApi(AuthService authService, CookieTokenExtractor tokenExtractor) {
        this.authService = authService;
        this.tokenExtractor = tokenExtractor;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        JwtToken token = authService.login(request.toCommand());

        Cookie cookie = new Cookie("token", token.token());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/login/check")
    public ResponseEntity<LoginCheckResponse> loginCheck(@AuthenticationPrincipal UserId userId) {
        User user = authService.loginCheck(new LoginCheckQuery(userId));

        return ResponseEntity.ok(LoginCheckResponse.from(user));
    }
}
