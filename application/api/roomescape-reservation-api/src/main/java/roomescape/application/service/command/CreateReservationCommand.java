package roomescape.application.service.command;

import roomescape.domain.reservation.vo.ReservationName;
import roomescape.domain.reservationtime.vo.ReservationTimeId;
import roomescape.domain.theme.vo.ThemeId;

public class CreateReservationCommand {

    private final String reservationName;

    private final Long reservationTimeId;

    private final Long themeId;

    public CreateReservationCommand(
            String reservationName,
            Long reservationTimeId,
            Long themeId
    ) {
        this.reservationName = reservationName;
        this.reservationTimeId = reservationTimeId;
        this.themeId = themeId;
    }

    public ReservationName getReservationName() {
        return new ReservationName(this.reservationName);
    }

    public ReservationTimeId getReservationTimeId() {
        return new ReservationTimeId(this.reservationTimeId);
    }

    public ThemeId getThemeId() {
        return new ThemeId(this.themeId);
    }
}
