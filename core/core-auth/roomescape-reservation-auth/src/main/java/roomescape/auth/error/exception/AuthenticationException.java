package roomescape.auth.error.exception;

import roomescape.auth.error.code.AuthErrorCode;
import roomescape.auth.error.key.AuthErrorKey;
import roomescape.auth.error.key.AuthErrorKeys;
import roomescape.domain.user.vo.UserEmail;
import roomescape.domain.user.vo.UserId;
import roomescape.domain.user.vo.UserPassword;

import static roomescape.auth.error.code.AuthErrorCode.*;

public class AuthenticationException extends AuthException {

    private static final String ERROR_KEY_PASSWORD = "password";
    private static final String ERROR_KEY_EMAIL = "email";

    public AuthenticationException(AuthErrorCode code, AuthErrorKeys keys) {
        super(code, keys);
    }

    public static AuthenticationException notMatchPassword(UserPassword password) {
        return new AuthenticationException(
                NOT_MATCH_PASSWORD,
                AuthErrorKeys.of(
                        new AuthErrorKey(
                                ERROR_KEY_PASSWORD,
                                password.password()
                        )
                )
        );
    }

    public static AuthenticationException notExistEmail(UserEmail email) {
        return new AuthenticationException(
                NOT_EXIST_USER,
                AuthErrorKeys.of(
                        new AuthErrorKey(
                                ERROR_KEY_EMAIL,
                                email.email()
                        )
                )
        );
    }

    public static AuthenticationException notFoundUser(UserId id) {
        return new AuthenticationException(
                NOT_EXIST_EMAIL,
                AuthErrorKeys.of(
                        new AuthErrorKey(
                                ERROR_KEY_EMAIL,
                                id.id().toString()
                        )
                )
        );
    }
}
