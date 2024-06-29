package roomescape.domain.reservation.validator;

import roomescape.domain.reservation.vo.CreateReservationTime;
import roomescape.domain.reservation.vo.ReservationId;
import roomescape.domain.reservation.vo.ReservationName;
import roomescape.domain.reservationtime.ReservationTime;
import roomescape.domain.theme.Theme;
import roomescape.error.exception.CreateReservationValidateException;
import roomescape.error.exception.DomainValidateException;

import java.util.Objects;

public final class CreateReservationValidator {

    private CreateReservationValidator() {
        throw new UnsupportedOperationException(this.getClass().getName() + "의 인스턴스는 생성되어서 안됩니다.");
    }

    public static void validate(
            ReservationId id,
            ReservationName name,
            ReservationTime time,
            Theme theme,
            CreateReservationTime createTime
    ) {
        validateNotNull(id, name, time, theme, createTime);
        validateNotEmpty(name);
        validateFuture(time, createTime);
    }

    private static void validateFuture(ReservationTime time, CreateReservationTime createTime) {
        if (time.isCreateTimeBefore(createTime)) {
            throw CreateReservationValidateException.pastTime(time);
        }
    }

    private static void validateNotNull(
            ReservationId id,
            ReservationName name,
            ReservationTime time,
            Theme theme,
            CreateReservationTime createTime
    ) {
        if (Objects.isNull(id)) {
            throw DomainValidateException.notToBeNull(ReservationId.class);
        }
        if (Objects.isNull(name)) {
            throw DomainValidateException.notToBeNull(ReservationName.class);
        }
        if (Objects.isNull(time)) {
            throw DomainValidateException.notToBeNull(ReservationTime.class);
        }
        if (Objects.isNull(theme)) {
            throw DomainValidateException.notToBeNull(Theme.class);
        }
        if (Objects.isNull(createTime)) {
            throw DomainValidateException.notToBeNull(CreateReservationTime.class);
        }
    }

    private static void validateNotEmpty(ReservationName name) {
        if (name.isEmpty()) {
            throw DomainValidateException.notToBeEmpty(name.getClass());
        }
    }
}
