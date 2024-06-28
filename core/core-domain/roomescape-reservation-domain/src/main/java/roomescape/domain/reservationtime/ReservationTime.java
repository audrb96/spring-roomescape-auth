package roomescape.domain.reservationtime;

import roomescape.domain.reservation.vo.ReservationDate;
import roomescape.domain.reservationtime.vo.ReservationTimeId;
import roomescape.domain.reservationtime.vo.ReservationTimeStartAt;
import roomescape.domain.theme.vo.ThemeId;
import roomescape.util.validator.ObjectValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
        ObjectValidator.validateNotNull(id, date, startAt);
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

    public String getFormatDateTime(String pattern) {
        return this.getDateTime().format(DateTimeFormatter.ofPattern(pattern));
    }

    public Long getId() {
        return id.id();
    }

    public LocalDate getDate() {
        return date.date();
    }

    public LocalTime getStartAt() {
        return this.startAt.startAt();
    }

    public Long getThemeId() {
        return this.themeId.id();
    }

    public boolean isBefore(LocalDateTime dateTime) {
        return this.getDateTime().isBefore(dateTime);
    }

    private LocalDateTime getDateTime() {
        return LocalDateTime.of(this.date.date(), this.startAt.startAt());
    }
}
