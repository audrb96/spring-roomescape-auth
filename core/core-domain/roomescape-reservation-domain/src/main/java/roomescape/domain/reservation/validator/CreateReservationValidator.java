package roomescape.domain.reservation.validator;

import roomescape.domain.reservation.vo.CreateReservationTime;
import roomescape.domain.reservationtime.ReservationTime;
import roomescape.error.exception.CreateReservationValidateException;
import roomescape.error.exception.DomainValidateException;

import java.util.Objects;

public class CreateReservationValidator {

    private final ReservationTime time;
    private final CreateReservationTime createTime;

    public CreateReservationValidator(ReservationTime time, CreateReservationTime createTime) {
        this.time = time;
        this.createTime = createTime;
    }

    public void validate() {
        this.validateNotNull(time, createTime);
        this.validateFuture(time, createTime);
    }

    private void validateNotNull(
            ReservationTime time,
            CreateReservationTime createTime
    ) {
        if (Objects.isNull(time)) {
            throw DomainValidateException.notToBeNull(ReservationTime.class);
        }
        if (Objects.isNull(createTime)) {
            throw DomainValidateException.notToBeNull(CreateReservationTime.class);
        }
    }

    private void validateFuture(ReservationTime time, CreateReservationTime createTime) {
        if (time.isCreateTimeBefore(createTime)) {
            throw CreateReservationValidateException.pastTime(time);
        }
    }
}
