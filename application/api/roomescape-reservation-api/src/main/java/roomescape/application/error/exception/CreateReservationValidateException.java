package roomescape.application.error.exception;

import roomescape.application.error.code.ApplicationErrorCode;
import roomescape.application.error.key.ApplicationErrorKey;
import roomescape.application.error.key.ApplicationErrorKeys;
import roomescape.domain.reservationtime.ReservationTime;

import static roomescape.application.error.code.ApplicationErrorCode.CANNOT_CREATE_EXIST_RESERVATION_AT_THIS_TIME;

public class CreateReservationValidateException extends ApplicationException {

    private static final String ERROR_KEY_TIME_ID = "timeId";

    public CreateReservationValidateException(ApplicationErrorCode code, ApplicationErrorKeys keys) {
        super(code, keys);
    }

    public static CreateReservationValidateException existTime(
            ReservationTime reservationTime
    ) {
        return new CreateReservationValidateException(
                CANNOT_CREATE_EXIST_RESERVATION_AT_THIS_TIME,
                ApplicationErrorKeys.of(
                        new ApplicationErrorKey(ERROR_KEY_TIME_ID, reservationTime.getId().toString())
                )
        );
    }
}
