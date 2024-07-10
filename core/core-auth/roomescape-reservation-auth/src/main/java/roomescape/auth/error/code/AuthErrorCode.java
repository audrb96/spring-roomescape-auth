package roomescape.auth.error.code;

public enum AuthErrorCode {

    INVALID_API_REQUEST_PARAMETER("유효하지 않은 API 요청입니다."),
    NOT_EXIST_EMAIL("존재하지 않는 이메일의 사용자입니다."),
    NOT_EXIST_USER("존재하지 않는 사용자입니다."),
    NOT_MATCH_PASSWORD("패스워드가 일치하지 않습니다.");

    private final String message;

    AuthErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
