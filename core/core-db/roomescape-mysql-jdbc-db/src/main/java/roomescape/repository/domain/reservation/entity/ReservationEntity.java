package roomescape.repository.domain.reservation.entity;

import roomescape.domain.reservation.Reservation;
import roomescape.domain.reservation.vo.ReservationDate;
import roomescape.domain.reservation.vo.ReservationId;
import roomescape.domain.reservationtime.vo.ReservationTimeId;
import roomescape.domain.theme.vo.ThemeId;
import roomescape.domain.user.vo.UserId;

import java.time.LocalDate;

public class ReservationEntity {

    private final Long id;
    private final Long userId;
    private final LocalDate date;
    private final Long reservationTimeId;
    private final Long themeId;

    public ReservationEntity(
            Long id,
            Long userId,
            LocalDate date,
            Long reservationTimeId,
            Long themeId
    ) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.reservationTimeId = reservationTimeId;
        this.themeId = themeId;
    }

    public static ReservationEntity from(Reservation reservation) {
        return new ReservationEntity(
                reservation.getId().id(),
                reservation.getUserId().id(),
                reservation.getDate().date(),
                reservation.getTimeId().id(),
                reservation.getThemeId().id()
        );
    }

    public ReservationEntity withId(Long id) {
        return new ReservationEntity(id, this.userId, date, this.reservationTimeId, themeId);
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getReservationTimeId() {
        return this.reservationTimeId;
    }

    public Long getThemeId() {
        return this.themeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public Reservation toDomain() {
        return Reservation.mappedBy(
                new ReservationId(this.id),
                new UserId(this.userId),
                new ReservationDate(this.date),
                new ReservationTimeId(this.reservationTimeId),
                new ThemeId(this.themeId)
        );
    }
}
