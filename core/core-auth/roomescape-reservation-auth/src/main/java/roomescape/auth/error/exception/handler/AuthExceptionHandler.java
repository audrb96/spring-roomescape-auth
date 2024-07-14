package roomescape.auth.error.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import roomescape.auth.error.dto.ErrorResponse;
import roomescape.auth.error.exception.AuthenticationException;
import roomescape.auth.error.factory.ResponseEntityFactory;
import roomescape.auth.error.logger.ExceptionLogger;
import roomescape.jwt.error.exception.JwtException;

@ControllerAdvice
public class AuthExceptionHandler {

    private final ExceptionLogger exceptionLogger = new ExceptionLogger(this.getClass());


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handle(AuthenticationException ex) {
        exceptionLogger.log(ex);

        return ResponseEntityFactory.unauthorized(ex);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponse> handle(JwtException ex) {
        exceptionLogger.log(ex);

        return ResponseEntityFactory.unauthorized(ex);
    }
}
