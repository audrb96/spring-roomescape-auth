package roomescape.jwt.error.exception;

import roomescape.jwt.JwtToken;
import roomescape.jwt.error.code.JwtErrorCode;
import roomescape.jwt.error.key.JwtErrorKey;
import roomescape.jwt.error.key.JwtErrorKeys;

import static roomescape.jwt.error.code.JwtErrorCode.*;

public class InvalidJwtException extends JwtException {

    private static final String ERROR_KEY_TOKEN = "token";

    public InvalidJwtException(JwtErrorCode code, JwtErrorKeys keys) {
        super(code, keys);
    }

    public static InvalidJwtException expiredToken(JwtToken token) {
        return new InvalidJwtException(
                EXPIRED_TOKEN,
                JwtErrorKeys.of(
                        new JwtErrorKey(
                                ERROR_KEY_TOKEN,
                                token.token()
                        )
                )
        );
    }

    public static InvalidJwtException unsupportedToken(JwtToken token) {
        return new InvalidJwtException(
                UNSUPPORTED_TOKEN,
                JwtErrorKeys.of(
                        new JwtErrorKey(
                                ERROR_KEY_TOKEN,
                                token.token()
                        )
                )
        );
    }

    public static InvalidJwtException malformedToken(JwtToken token) {
        return new InvalidJwtException(
                MALFORMED_TOKEN,
                JwtErrorKeys.of(
                        new JwtErrorKey(
                                ERROR_KEY_TOKEN,
                                token.token()
                        )
                )
        );
    }

    public static InvalidJwtException signatureToken(JwtToken token) {
        return new InvalidJwtException(
                SIGNATURE_TOKEN,
                JwtErrorKeys.of(
                        new JwtErrorKey(
                                ERROR_KEY_TOKEN,
                                token.token()
                        )
                )
        );
    }

    public static InvalidJwtException tokenClaimsStringEmpty(JwtToken token) {
        return new InvalidJwtException(
                JWT_CLAIMS_STRING_EMPTY,
                JwtErrorKeys.of(
                        new JwtErrorKey(
                                ERROR_KEY_TOKEN,
                                token.token()
                        )
                )
        );
    }
}
