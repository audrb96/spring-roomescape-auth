package roomescape.application.error.exception;

import roomescape.application.error.code.ApplicationErrorCode;
import roomescape.application.error.key.ApplicationErrorKey;
import roomescape.application.error.key.ApplicationErrorKeys;
import roomescape.domain.reservationtime.ReservationTime;
import roomescape.domain.theme.vo.ThemeId;

import static roomescape.application.error.code.ApplicationErrorCode.CANNOT_CREATE_EXIST_RESERVATION_TIME;
import static roomescape.application.error.code.ApplicationErrorCode.CANNOT_CREATE_RESERVATION_TIME_NOT_EXIST_THEME;

public class CreateReservationTimeValidateException extends ApplicationException {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm";

    private static final String ERROR_KEY_NAME_DATE = "date";
    private static final String ERROR_KEY_NAME_START_AT = "startAt";
    private static final String ERROR_KEY_NAME_THEME_ID = "themeId";

    public CreateReservationTimeValidateException(ApplicationErrorCode code, ApplicationErrorKeys keys) {
        super(code, keys);
    }

    public static CreateReservationTimeValidateException existEquals(ReservationTime reservationTime) {
        return new CreateReservationTimeValidateException(
                CANNOT_CREATE_EXIST_RESERVATION_TIME,
                ApplicationErrorKeys.of(
                        new ApplicationErrorKey(
                                ERROR_KEY_NAME_THEME_ID,
                                reservationTime.getThemeId().toString()
                        ),
                        new ApplicationErrorKey(
                                ERROR_KEY_NAME_DATE,
                                reservationTime.getFormatDate(DATE_FORMAT)
                        ),
                        new ApplicationErrorKey(
                                ERROR_KEY_NAME_START_AT,
                                reservationTime.getFormatStartAt(TIME_FORMAT)
                        )
                )
        );
    }

    public static CreateReservationTimeValidateException notExistTheme(ThemeId id) {
        return new CreateReservationTimeValidateException(
                CANNOT_CREATE_RESERVATION_TIME_NOT_EXIST_THEME,
                ApplicationErrorKeys.of(
                        new ApplicationErrorKey(
                                ERROR_KEY_NAME_THEME_ID,
                                id.id().toString()
                        )
                )
        );
    }
}
