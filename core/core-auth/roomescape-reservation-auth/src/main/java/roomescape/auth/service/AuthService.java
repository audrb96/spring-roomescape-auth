package roomescape.auth.service;

import org.springframework.stereotype.Service;
import roomescape.auth.common.component.password.encoder.UserPasswordEncoder;
import roomescape.auth.error.exception.AuthenticationException;
import roomescape.auth.service.command.LoginCommand;
import roomescape.auth.service.component.reader.UserReader;
import roomescape.auth.service.query.LoginCheckQuery;
import roomescape.domain.user.User;
import roomescape.jwt.JwtToken;
import roomescape.jwt.component.provider.JwtTokenProvider;

@Service
public class AuthService {

    private final JwtTokenProvider tokenProvider;
    private final UserPasswordEncoder passwordEncoder;
    private final UserReader userReader;

    public AuthService(
            JwtTokenProvider tokenProvider,
            UserPasswordEncoder passwordEncoder,
            UserReader userReader
    ) {
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.userReader = userReader;
    }

    public JwtToken login(LoginCommand command) {
        User user = userReader.readByEmail(command.fetchEmail());

        if (!passwordEncoder.matches(command.fetchPassword(), user.getPassword())) {
            throw AuthenticationException.notMatchPassword(command.fetchPassword());
        }

        return tokenProvider.provide(user.getId());
    }

    public User loginCheck(LoginCheckQuery query) {
        return userReader.readById(query.getUserId());
    }
}
