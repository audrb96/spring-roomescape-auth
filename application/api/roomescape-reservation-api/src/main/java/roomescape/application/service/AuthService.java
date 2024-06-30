package roomescape.application.service;

import org.springframework.stereotype.Service;
import roomescape.application.service.command.LoginCommand;
import roomescape.application.service.component.reader.UserReader;
import roomescape.application.service.query.LoginCheckQuery;
import roomescape.domain.auth.LoginCheck;
import roomescape.domain.user.User;
import roomescape.domain.user.vo.UserId;
import roomescape.jwt.JwtToken;
import roomescape.jwt.decoder.JwtTokenDecoder;
import roomescape.jwt.provider.JwtTokenProvider;

@Service
public class AuthService {

    private final JwtTokenProvider tokenProvider;
    private final JwtTokenDecoder decoder;
    private final UserReader userReader;

    public AuthService(
            JwtTokenProvider tokenProvider,
            JwtTokenDecoder decoder,
            UserReader userReader
    ) {
        this.tokenProvider = tokenProvider;
        this.decoder = decoder;
        this.userReader = userReader;
    }

    public JwtToken login(LoginCommand command) {
        User user = userReader.readByEmail(command.toUserEmail());

        return tokenProvider.createToken(user.getId());
    }

    public LoginCheck loginCheck(LoginCheckQuery query) {
        UserId userId = decoder.decode(query.getToken());
        User user = userReader.readById(userId);

        return new LoginCheck(user.getName());
    }
}
