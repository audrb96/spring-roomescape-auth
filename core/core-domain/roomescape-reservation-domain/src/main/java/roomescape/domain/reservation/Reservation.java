package roomescape.domain.reservation;

import roomescape.domain.reservation.validator.CreateReservationValidator;
import roomescape.domain.reservation.validator.ReservationValidator;
import roomescape.domain.reservation.vo.ReservationId;
import roomescape.domain.reservation.vo.ReservationName;
import roomescape.domain.reservationtime.ReservationTime;
import roomescape.domain.reservationtime.vo.ReservationTimeId;
import roomescape.domain.theme.Theme;
import roomescape.domain.theme.vo.ThemeId;
import roomescape.util.clockholder.SystemClockHolder;

public class Reservation {

    private final ReservationId id;

    private final ReservationName name;

    private final ReservationTimeId timeId;

    private final ThemeId themeId;


    private Reservation(
            ReservationId id,
            ReservationName name,
            ReservationTimeId timeId,
            ThemeId themeId
    ) {
        ReservationValidator.validate(id, name, timeId, themeId);
        this.timeId = timeId;
        this.themeId = themeId;
        this.id = id;
        this.name = name;
    }

    public static Reservation create(
            ReservationId id,
            ReservationName name,
            ReservationTime time,
            Theme theme
    ) {
        CreateReservationValidator.validate(id, name, time, theme, new SystemClockHolder());
        return new Reservation(
                id,
                name,
                new ReservationTimeId(time.getId()),
                new ThemeId(theme.getId())
        );
    }

    public static Reservation of(
            ReservationId id,
            ReservationName name,
            ReservationTimeId timeId,
            ThemeId themeId
    ) {
        return new Reservation(id, name, timeId, themeId);
    }

    public Long getId() {
        return id.id();
    }

    public String getName() {
        return name.name();
    }

    public Long getReservationTimeId() {
        return timeId.id();
    }

    public Long getThemeId() {
        return this.themeId.id();
    }
}
