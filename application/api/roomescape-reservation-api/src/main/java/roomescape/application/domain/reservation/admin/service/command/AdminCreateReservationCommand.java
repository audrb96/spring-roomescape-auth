package roomescape.application.domain.reservation.admin.service.command;

import roomescape.domain.reservation.vo.ReservationDate;
import roomescape.domain.reservationtime.vo.ReservationTimeId;
import roomescape.domain.theme.vo.ThemeId;
import roomescape.domain.user.vo.UserId;

import java.time.LocalDate;

public class AdminCreateReservationCommand {

    private final Long userId;

    private final LocalDate date;

    private final Long timeId;

    private final Long themeId;

    public AdminCreateReservationCommand(Long userId, LocalDate date, Long timeId, Long themeId) {
        this.userId = userId;
        this.date = date;
        this.timeId = timeId;
        this.themeId = themeId;
    }

    public UserId fetchUserId() {
        return new UserId(this.userId);
    }

    public ReservationTimeId fetchReservationTimeId() {
        return new ReservationTimeId(this.timeId);
    }

    public ThemeId fetchThemeId() {
        return new ThemeId(this.themeId);
    }

    public ReservationDate fetchReservationDate() {
        return new ReservationDate(this.date);
    }
}
