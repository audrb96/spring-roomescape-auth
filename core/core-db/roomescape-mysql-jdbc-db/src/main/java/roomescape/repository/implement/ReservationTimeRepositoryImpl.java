package roomescape.repository.implement;

import org.springframework.stereotype.Repository;
import roomescape.domain.reservation.vo.ReservationDate;
import roomescape.domain.reservationtime.ReservationTime;
import roomescape.domain.reservationtime.ReservationTimeRepository;
import roomescape.domain.reservationtime.ReservationTimes;
import roomescape.domain.reservationtime.vo.ReservationTimeId;
import roomescape.domain.theme.vo.ThemeId;
import roomescape.repository.ReservationTimeJdbcRepository;
import roomescape.repository.entity.ReservationTimeEntity;

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
    public Optional<ReservationTime> findById(ReservationTimeId id) {
        return repository.findById(id.id())
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
    public void delete(ReservationTimeId id) {
        repository.delete(id.id());
    }

    @Override
    public ReservationTimes findByDateAndThemeId(ReservationDate date, ThemeId themeId) {
        return new ReservationTimes(
                repository.findByDateAndThemeId(date.date(), themeId.id()).stream()
                        .map(ReservationTimeEntity::toDomain)
                        .collect(Collectors.toList())
        );
    }
}
