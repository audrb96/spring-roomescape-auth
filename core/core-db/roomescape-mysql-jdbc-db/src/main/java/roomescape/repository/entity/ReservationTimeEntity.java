package roomescape.repository.entity;

import roomescape.domain.reservation.vo.ReservationDate;
import roomescape.domain.reservationtime.ReservationTime;
import roomescape.domain.reservationtime.vo.ReservationTimeId;
import roomescape.domain.reservationtime.vo.ReservationTimeStartAt;
import roomescape.domain.theme.vo.ThemeId;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationTimeEntity {

    private final Long id;

    private final Long themeId;

    private final LocalDate date;

    private final LocalTime startAt;

    public ReservationTimeEntity(Long id, Long themeId, LocalDate date, LocalTime startAt) {
        this.id = id;
        this.themeId = themeId;
        this.date = date;
        this.startAt = startAt;
    }

    public static ReservationTimeEntity from(ReservationTime reservationTime) {
        return new ReservationTimeEntity(
                reservationTime.getId().id(),
                reservationTime.getThemeId().id(),
                reservationTime.getDate().date(),
                reservationTime.getStartAt().startAt()
        );
    }

    public Long getId() {
        return this.id;
    }

    public Long getThemeId() {
        return themeId;
    }

    public LocalTime getStartAt() {
        return this.startAt;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public ReservationTimeEntity withId(Long id) {
        return new ReservationTimeEntity(id, this.themeId, this.date, this.startAt);
    }

    public ReservationTime toDomain() {
        return new ReservationTime(
                new ReservationTimeId(this.id),
                new ThemeId(this.themeId),
                new ReservationDate(this.date),
                new ReservationTimeStartAt(this.startAt)
        );
    }
}
