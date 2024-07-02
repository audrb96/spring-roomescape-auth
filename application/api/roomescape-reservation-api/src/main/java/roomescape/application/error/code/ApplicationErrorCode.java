package roomescape.application.error.code;

public enum ApplicationErrorCode {

    RUN_TIME_EXCEPTION("런타임 에러"),
    INVALID_API_REQUEST_PARAMETER("유효하지 않은 API 요청입니다."),
    CANNOT_DELETE_EXIST_RESERVATION_AT_THIS_TIME("이 시간에 존재하는 예약이 있어 삭제할 수 없습니다."),
    CANNOT_CREATE_EXIST_RESERVATION_AT_THIS_TIME("이 시간에 존재하는 예약이 있어 예약을 생성할 수 없습니다."),
    CANNOT_CREATE_EXIST_RESERVATION_TIME("이미 존재하는 예약 시간입니다."),
    CANNOT_CREATE_RESERVATION_TIME_NOT_EXIST_THEME("테마가 존재하지 않아 예약 시간을 생성할 수 없습니다.");

    private final String message;

    ApplicationErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
