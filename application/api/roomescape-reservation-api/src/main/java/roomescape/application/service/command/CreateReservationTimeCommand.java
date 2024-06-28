package roomescape.application.service.command;

import roomescape.domain.reservation.vo.ReservationDate;
import roomescape.domain.reservationtime.ReservationTime;
import roomescape.domain.reservationtime.vo.ReservationTimeId;
import roomescape.domain.reservationtime.vo.ReservationTimeStartAt;
import roomescape.domain.theme.vo.ThemeId;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateReservationTimeCommand {

    private final Long themeId;

    private final LocalDate date;

    private final LocalTime startAt;

    public CreateReservationTimeCommand(Long themeId, LocalDate date, LocalTime startAt) {
        this.themeId = themeId;
        this.date = date;
        this.startAt = startAt;
    }

    public ReservationTime toReservationTime() {
        return new ReservationTime(
                ReservationTimeId.empty(),
                new ThemeId(this.themeId),
                new ReservationDate(this.date),
                new ReservationTimeStartAt(this.startAt)
        );
    }
}
