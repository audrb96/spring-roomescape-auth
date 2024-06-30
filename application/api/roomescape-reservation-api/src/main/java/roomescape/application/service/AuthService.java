package roomescape.application.service;

import org.springframework.stereotype.Service;
import roomescape.application.service.command.LoginCommand;
import roomescape.application.service.component.reader.UserReader;
import roomescape.domain.user.User;
import roomescape.jwt.JwtToken;
import roomescape.jwt.JwtTokenProvider;

@Service
public class AuthService {

    private final JwtTokenProvider tokenProvider;
    private final UserReader userReader;

    public AuthService(
            JwtTokenProvider tokenProvider,
            UserReader userReader
    ) {
        this.tokenProvider = tokenProvider;
        this.userReader = userReader;
    }

    public JwtToken login(LoginCommand command) {
        User user = userReader.readByEmail(command.toUserEmail());
        
        return tokenProvider.createToken(user.getId());
    }
}
