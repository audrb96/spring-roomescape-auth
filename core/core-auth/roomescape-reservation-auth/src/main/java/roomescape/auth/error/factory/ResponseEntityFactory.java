package roomescape.auth.error.factory;

import org.springframework.http.ResponseEntity;
import roomescape.auth.error.dto.ErrorResponse;
import roomescape.auth.error.exception.AuthException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public final class ResponseEntityFactory {

    private ResponseEntityFactory() {
        throw new UnsupportedOperationException(this.getClass().getName() + "의 인스턴스는 생성되어서 안됩니다.");
    }

    public static ResponseEntity<ErrorResponse> unauthorized(AuthException exception) {
        return ResponseEntity
                .status(UNAUTHORIZED)
                .body(ErrorResponse.from(exception));
    }
}
