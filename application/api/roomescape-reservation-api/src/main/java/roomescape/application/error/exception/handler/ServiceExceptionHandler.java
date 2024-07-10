package roomescape.application.error.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import roomescape.application.error.dto.ErrorResponse;
import roomescape.application.error.exception.ApplicationException;
import roomescape.application.error.factory.ResponseEntityFactory;
import roomescape.application.error.logger.ExceptionLogger;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ServiceExceptionHandler {

    private final ExceptionLogger exceptionLogger = new ExceptionLogger(this.getClass());

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handleException(ApplicationException ex) {
        exceptionLogger.log(ex);

        return ResponseEntityFactory.badRequest(ex);
    }
}
