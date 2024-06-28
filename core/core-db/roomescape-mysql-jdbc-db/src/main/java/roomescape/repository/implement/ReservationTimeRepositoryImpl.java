package roomescape.repository.implement;

import org.springframework.stereotype.Repository;
import roomescape.domain.reservationtime.ReservationTime;
import roomescape.domain.reservationtime.ReservationTimeRepository;
import roomescape.domain.reservationtime.ReservationTimes;
import roomescape.repository.ReservationTimeJdbcRepository;
import roomescape.repository.entity.ReservationTimeEntity;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ReservationTimeRepositoryImpl implements ReservationTimeRepository {

    private final ReservationTimeJdbcRepository repository;

    public ReservationTimeRepositoryImpl(ReservationTimeJdbcRepository repository) {
        this.repository = repository;
    }

    @Override
    public ReservationTime save(ReservationTime reservationTime) {
        return repository.save(ReservationTimeEntity.from(reservationTime))
                .toDomain();
    }

    @Override
    public Optional<ReservationTime> findById(Long timeId) {
        return repository.findById(timeId)
                .map(ReservationTimeEntity::toDomain);
    }

    @Override
    public boolean existEquals(ReservationTime reservationTime) {
        return repository.findEquals(ReservationTimeEntity.from(reservationTime)).isPresent();
    }

    @Override
    public ReservationTimes findAll() {
        return new ReservationTimes(
                repository.findAll().stream()
                        .map(ReservationTimeEntity::toDomain)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public void delete(Long timeId) {
        repository.delete(timeId);
    }

    @Override
    public ReservationTimes findByDateAndThemeId(LocalDate date, Long themeId) {
        return new ReservationTimes(
                repository.findByDateAndThemeId(date, themeId).stream()
                        .map(ReservationTimeEntity::toDomain)
                        .collect(Collectors.toList())
        );
    }
}
