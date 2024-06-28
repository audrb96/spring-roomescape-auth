package roomescape.domain.reservation;

import java.util.Optional;

public interface ReservationRepository {

    Reservation save(Reservation reservation);

    void delete(Long id);

    Optional<Reservation> findById(Long reservationId);

    ReservationViews findAllReservationViews();

    boolean existByTimeId(Long timeId);

}
