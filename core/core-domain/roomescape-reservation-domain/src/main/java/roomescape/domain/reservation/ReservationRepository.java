package roomescape.domain.reservation;

import roomescape.domain.reservation.vo.ReservationId;
import roomescape.domain.reservationtime.vo.ReservationTimeId;

import java.util.Optional;

public interface ReservationRepository {

    Reservation save(Reservation reservation);

    void delete(ReservationId id);

    Optional<Reservation> findById(ReservationId id);

    ReservationViews findAllReservationViews();

    boolean existByTimeId(ReservationTimeId timeId);

}
