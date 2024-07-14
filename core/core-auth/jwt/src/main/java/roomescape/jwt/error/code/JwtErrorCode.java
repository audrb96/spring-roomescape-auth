package roomescape.jwt.error.code;

public enum JwtErrorCode {

    EXPIRED_TOKEN("만료된 토큰입니다."),
    UNSUPPORTED_TOKEN("지원하지 않는 토큰입니다."),
    MALFORMED_TOKEN("유효하지 않은 토큰입니다."),
    SIGNATURE_TOKEN("유효하지 않은 시그니처의 토큰입니다."),
    JWT_CLAIMS_STRING_EMPTY("JWT claims이 empty여서는 안됩니다.");

    private final String message;

    JwtErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
