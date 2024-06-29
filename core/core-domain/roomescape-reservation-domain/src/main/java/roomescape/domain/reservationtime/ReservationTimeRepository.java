package roomescape.domain.reservationtime;

import roomescape.domain.reservation.vo.ReservationDate;
import roomescape.domain.reservationtime.vo.ReservationTimeId;
import roomescape.domain.theme.vo.ThemeId;

import java.util.Optional;

public interface ReservationTimeRepository {

    ReservationTime save(ReservationTime reservationTime);

    Optional<ReservationTime> findById(ReservationTimeId timeId);

    boolean existEquals(ReservationTime reservationTime);

    ReservationTimes findAll();

    void delete(ReservationTimeId id);

    ReservationTimes findByDateAndThemeId(ReservationDate date, ThemeId themeId);
}
