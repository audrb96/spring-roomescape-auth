package roomescape.application.service.command;

import roomescape.domain.reservation.vo.ReservationDate;
import roomescape.domain.reservationtime.ReservationTime;
import roomescape.domain.reservationtime.vo.ReservationTimeId;
import roomescape.domain.reservationtime.vo.ReservationTimeStartAt;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateReservationTimeCommand {

    private final LocalDate date;

    private final LocalTime startAt;

    public CreateReservationTimeCommand(LocalDate date, LocalTime startAt) {
        this.date = date;
        this.startAt = startAt;
    }

    public ReservationTime toReservationTime() {
        return new ReservationTime(
                ReservationTimeId.empty(),
                new ReservationDate(this.date),
                new ReservationTimeStartAt(this.startAt)
        );
    }
}
