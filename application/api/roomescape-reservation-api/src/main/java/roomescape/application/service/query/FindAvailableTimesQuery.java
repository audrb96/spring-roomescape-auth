package roomescape.application.service.query;

import roomescape.domain.reservation.vo.ReservationDate;
import roomescape.domain.theme.vo.ThemeId;

import java.time.LocalDate;

public class FindAvailableTimesQuery {

    private final LocalDate date;

    private final Long themeId;

    public FindAvailableTimesQuery(LocalDate date, Long themeId) {
        this.date = date;
        this.themeId = themeId;
    }

    public ReservationDate toReservationDate() {
        return new ReservationDate(date);
    }

    public ThemeId toThemeId() {
        return new ThemeId(themeId);
    }
}
