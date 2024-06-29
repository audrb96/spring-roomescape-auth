package roomescape.error.exception;

import roomescape.domain.reservationtime.vo.ReservationTimeId;
import roomescape.domain.theme.vo.ThemeId;
import roomescape.error.code.DomainErrorCode;
import roomescape.error.key.DomainErrorKey;
import roomescape.error.key.DomainErrorKeys;

import static roomescape.error.code.DomainErrorCode.NOT_FOUND_RESERVATION_TIME;
import static roomescape.error.code.DomainErrorCode.NOT_FOUND_THEME;

public class NotFoundDomainException extends DomainException {

    private static final String RESERVATION_TIME_ID = "timeId";
    private static final String THEME_ID = "themeId";

    public NotFoundDomainException(DomainErrorCode code, DomainErrorKeys keys) {
        super(code, keys);
    }

    public static NotFoundDomainException notFoundReservationTime(ReservationTimeId id) {
        return new NotFoundDomainException(
                NOT_FOUND_RESERVATION_TIME,
                DomainErrorKeys.of(
                        new DomainErrorKey(RESERVATION_TIME_ID, id.id().toString())
                )
        );
    }

    public static NotFoundDomainException notFoundTheme(ThemeId id) {
        return new NotFoundDomainException(
                NOT_FOUND_THEME,
                DomainErrorKeys.of(
                        new DomainErrorKey(THEME_ID, id.id().toString())
                )
        );
    }
}
