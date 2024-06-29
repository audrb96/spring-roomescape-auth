package roomescape.repository.implement;

import org.springframework.stereotype.Repository;
import roomescape.domain.reservation.Reservation;
import roomescape.domain.reservation.ReservationRepository;
import roomescape.domain.reservation.ReservationViews;
import roomescape.domain.reservation.vo.ReservationId;
import roomescape.domain.reservationtime.vo.ReservationTimeId;
import roomescape.repository.ReservationJdbcRepository;
import roomescape.repository.entity.ReservationEntity;
import roomescape.repository.projection.ReservationViewProjection;

import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationJdbcRepository repository;

    public ReservationRepositoryImpl(ReservationJdbcRepository repository) {
        this.repository = repository;
    }

    @Override
    public Reservation save(Reservation reservation) {
        return repository.save(ReservationEntity.from(reservation))
                .toDomain();
    }

    @Override
    public void delete(ReservationId id) {
        repository.delete(id.id());
    }

    @Override
    public Optional<Reservation> findById(ReservationId id) {
        return repository.findById(id.id())
                .map(ReservationEntity::toDomain);
    }

    @Override
    public ReservationViews findAllReservationViews() {
        return new ReservationViews(
                repository.findAllReservationViewProjection().stream()
                        .map(ReservationViewProjection::toDomain)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public boolean existByTimeId(ReservationTimeId timeId) {
        return repository.findByTimeId(timeId.id()).isPresent();
    }
}
