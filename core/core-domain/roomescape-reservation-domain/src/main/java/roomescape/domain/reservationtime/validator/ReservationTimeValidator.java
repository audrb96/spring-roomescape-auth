package roomescape.domain.reservationtime.validator;

import roomescape.domain.reservation.vo.ReservationDate;
import roomescape.domain.reservationtime.vo.ReservationTimeId;
import roomescape.domain.reservationtime.vo.ReservationTimeStartAt;
import roomescape.domain.theme.vo.ThemeId;
import roomescape.error.exception.DomainValidateException;

import java.util.Objects;

public final class ReservationTimeValidator {

    private ReservationTimeValidator() {
        throw new UnsupportedOperationException(this.getClass().getName() + "의 인스턴스는 생성되어서 안됩니다.");
    }

    public static void validate(
            ReservationTimeId id,
            ThemeId themeId,
            ReservationDate date,
            ReservationTimeStartAt startAt
    ) {
        validateNotNull(id, themeId, date, startAt);
    }

    private static void validateNotNull(
            ReservationTimeId id,
            ThemeId themeId,
            ReservationDate date,
            ReservationTimeStartAt startAt
    ) {
        if (Objects.isNull(id)) {
            throw DomainValidateException.notToBeNull(ReservationTimeId.class);
        }
        if (Objects.isNull(themeId)) {
            throw DomainValidateException.notToBeNull(ThemeId.class);
        }
        if (Objects.isNull(date)) {
            throw DomainValidateException.notToBeNull(ReservationDate.class);
        }
        if (Objects.isNull(startAt)) {
            throw DomainValidateException.notToBeNull(ReservationTimeStartAt.class);
        }
    }
}
