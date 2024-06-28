package roomescape.domain.reservationtime;

import java.util.Optional;

public interface ReservationTimeRepository {

    ReservationTime save(ReservationTime reservationTime);

    Optional<ReservationTime> findById(Long timeId);

    boolean existEquals(ReservationTime reservationTime);

    ReservationTimes findAll();

    void delete(Long timeId);
}
