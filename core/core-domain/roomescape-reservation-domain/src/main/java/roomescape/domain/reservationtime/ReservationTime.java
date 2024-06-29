package roomescape.domain.reservationtime;

import roomescape.domain.reservation.vo.ReservationDate;
import roomescape.domain.reservationtime.validator.ReservationTimeValidator;
import roomescape.domain.reservationtime.vo.ReservationTimeId;
import roomescape.domain.reservationtime.vo.ReservationTimeStartAt;
import roomescape.domain.theme.vo.ThemeId;

import java.time.LocalDateTime;

public class ReservationTime {

    private final ReservationTimeId id;

    private final ThemeId themeId;

    private final ReservationDate date;

    private final ReservationTimeStartAt startAt;

    public ReservationTime(
            ReservationTimeId id,
            ThemeId themeId,
            ReservationDate date,
            ReservationTimeStartAt startAt
    ) {
        ReservationTimeValidator.validate(id, themeId, date, startAt);
        this.themeId = themeId;
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

    public ThemeId getThemeId() {
        return themeId;
    }

    public ReservationDate getDate() {
        return date;
    }

    public ReservationTimeStartAt getStartAt() {
        return startAt;
    }

    public boolean isBefore(LocalDateTime dateTime) {
        return this.getDateTime().isBefore(dateTime);
    }

    private LocalDateTime getDateTime() {
        return LocalDateTime.of(this.date.date(), this.startAt.startAt());
    }
}
