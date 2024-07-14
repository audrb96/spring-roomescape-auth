package roomescape.jwt.error.exception;

import roomescape.jwt.error.code.JwtErrorCode;
import roomescape.jwt.error.key.JwtErrorKeys;

public class JwtException extends RuntimeException {

    private final JwtErrorCode code;

    private final JwtErrorKeys keys;

    public JwtException(JwtErrorCode code, JwtErrorKeys keys) {
        super(keys.toMessage() + code.getMessage());
        this.code = code;
        this.keys = keys;
    }

    public JwtErrorCode getCode() {
        return code;
    }

    public String getCodeName() {
        return this.code.name();
    }

    public JwtErrorKeys getKeys() {
        return keys;
    }
}
