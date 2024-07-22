package roomescape.domain.reservation;

import roomescape.domain.reservation.validator.CreateReservationValidator;
import roomescape.domain.reservation.validator.ReservationValidator;
import roomescape.domain.reservation.vo.CreateReservationTime;
import roomescape.domain.reservation.vo.ReservationDate;
import roomescape.domain.reservation.vo.ReservationId;
import roomescape.domain.reservationtime.ReservationTime;
import roomescape.domain.reservationtime.vo.ReservationTimeId;
import roomescape.domain.theme.vo.ThemeId;
import roomescape.domain.user.vo.UserId;

public class Reservation {

    private final ReservationId id;

    private final UserId userId;

    private final ReservationDate date;

    private final ReservationTimeId timeId;

    private final ThemeId themeId;

    private Reservation(
            ReservationId id,
            UserId userId,
            ReservationDate date,
            ReservationTimeId timeId,
            ThemeId themeId
    ) {
        ReservationValidator.validate(id, userId, timeId, themeId);
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.timeId = timeId;
        this.themeId = themeId;
    }

    public static Reservation create(
            ReservationId id,
            UserId userId,
            ReservationDate date,
            ReservationTime time,
            ThemeId themeId,
            CreateReservationTime createTime
    ) {
        CreateReservationValidator validator = new CreateReservationValidator(date, time, createTime);
        validator.validate();

        return new Reservation(id, userId, date, time.getId(), themeId);
    }

    public static Reservation mappedBy(
            ReservationId id,
            UserId userId,
            ReservationDate date,
            ReservationTimeId timeId,
            ThemeId themeId
    ) {
        return new Reservation(id, userId, date, timeId, themeId);
    }

    public ReservationId getId() {
        return this.id;
    }

    public UserId getUserId() {
        return userId;
    }

    public ReservationTimeId getTimeId() {
        return this.timeId;
    }

    public ThemeId getThemeId() {
        return this.themeId;
    }

    public String getFormatDate(String pattern) {
        return date.getFormat(pattern);
    }

    public ReservationDate getDate() {
        return date;
    }
}
