package roomescape.domain.reservationtime;

import roomescape.domain.reservation.vo.CreateReservationTime;
import roomescape.domain.reservation.vo.ReservationDate;
import roomescape.domain.reservationtime.validator.ReservationTimeValidator;
import roomescape.domain.reservationtime.vo.ReservationTimeId;
import roomescape.domain.reservationtime.vo.ReservationTimeStartAt;

import java.time.LocalDateTime;

public class ReservationTime {

    private final ReservationTimeId id;

    private final ReservationDate date;

    private final ReservationTimeStartAt startAt;

    public ReservationTime(
            ReservationTimeId id,
            ReservationDate date,
            ReservationTimeStartAt startAt
    ) {
        ReservationTimeValidator.validate(id, date, startAt);
        this.id = id;
        this.date = date;
        this.startAt = startAt;
    }

    public String getFormatStartAt(String pattern) {
        return startAt.getFormat(pattern);
    }

    public String getFormatDate(String pattern) {
        return date.getFormat(pattern);
    }

    public ReservationTimeId getId() {
        return id;
    }

    public ReservationDate getDate() {
        return date;
    }

    public ReservationTimeStartAt getStartAt() {
        return startAt;
    }

    public boolean isCreateTimeBefore(CreateReservationTime createTime) {
        return this.getDateTime().isBefore(createTime.time());
    }

    private LocalDateTime getDateTime() {
        return LocalDateTime.of(this.date.date(), this.startAt.startAt());
    }
}
