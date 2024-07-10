package roomescape.application.error.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import roomescape.application.error.dto.ErrorResponse;
import roomescape.application.error.factory.ResponseEntityFactory;
import roomescape.application.error.logger.ExceptionLogger;

@RestControllerAdvice
public class ApiExceptionHandler {

    private final ExceptionLogger exceptionLogger = new ExceptionLogger(this.getClass());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException ex) {
        exceptionLogger.log(ex);

        return ResponseEntityFactory.badRequest(ex);
    }
}
