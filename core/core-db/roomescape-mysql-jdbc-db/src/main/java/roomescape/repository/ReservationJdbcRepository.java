package roomescape.repository;

import roomescape.repository.entity.ReservationEntity;
import roomescape.repository.projection.ReservationViewProjection;

import java.util.List;
import java.util.Optional;

public interface ReservationJdbcRepository {

    ReservationEntity save(ReservationEntity entity);

    List<ReservationEntity> findAll();

    void delete(Long id);

    Optional<ReservationEntity> findById(Long id);

    List<ReservationViewProjection> findAllReservationViewProjection();

    Optional<ReservationEntity> findByTimeId(Long timeId);
}
