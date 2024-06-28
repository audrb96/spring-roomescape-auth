package roomescape.repository.mysql;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import roomescape.repository.ReservationTimeJdbcRepository;
import roomescape.repository.entity.ReservationTimeEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class MySQLJdbcReservationTimeRepository implements ReservationTimeJdbcRepository {

    private static final String TABLE_COLUMN_ID = "id";
    private static final String TABLE_COLUMN_THEME_ID = "theme_id";
    private static final String TABLE_COLUMN_DATE = "date";
    private static final String TABLE_COLUMN_START_AT = "start_at";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public MySQLJdbcReservationTimeRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public ReservationTimeEntity save(ReservationTimeEntity entity) {
        String sql = "INSERT INTO reservation_time (id, theme_id, date, start_at) VALUES (:id, :theme_id, :date, :start_at) " +
                "ON DUPLICATE KEY UPDATE theme_id = VALUES(theme_id), date = VALUES(date), start_at = VALUES(start_at)";

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(TABLE_COLUMN_ID, entity.getId())
                .addValue(TABLE_COLUMN_THEME_ID, entity.getThemeId())
                .addValue(TABLE_COLUMN_DATE, entity.getDate())
                .addValue(TABLE_COLUMN_START_AT, entity.getStartAt());

        namedParameterJdbcTemplate.update(sql, sqlParameterSource, generatedKeyHolder);

        if (Objects.isNull(generatedKeyHolder.getKey())) {
            return entity;
        }

        return entity.withId(generatedKeyHolder.getKey().longValue());
    }

    @Override
    public Optional<ReservationTimeEntity> findById(Long id) {
        String sql = "SELECT id, theme_id, date, start_at FROM reservation_time WHERE id = :id";

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(TABLE_COLUMN_ID, id);

        try {
            return Optional.ofNullable(
                    namedParameterJdbcTemplate.queryForObject(
                            sql,
                            sqlParameterSource,
                            (resultSet, rowNum) -> new ReservationTimeEntity(
                                    resultSet.getLong(TABLE_COLUMN_ID),
                                    resultSet.getLong(TABLE_COLUMN_THEME_ID),
                                    resultSet.getDate(TABLE_COLUMN_DATE).toLocalDate(),
                                    resultSet.getTime(TABLE_COLUMN_START_AT).toLocalTime()
                            )
                    )
            );
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ReservationTimeEntity> findEquals(ReservationTimeEntity entity) {
        String sql = "SELECT id, theme_id, date, start_at FROM reservation_time " +
                "WHERE date = :date and start_at = :start_at and theme_id = :theme_id";

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(TABLE_COLUMN_DATE, entity.getDate())
                .addValue(TABLE_COLUMN_START_AT, entity.getStartAt())
                .addValue(TABLE_COLUMN_THEME_ID, entity.getThemeId());

        try {
            return Optional.ofNullable(
                    namedParameterJdbcTemplate.queryForObject(
                            sql,
                            sqlParameterSource,
                            (resultSet, rowNum) -> new ReservationTimeEntity(
                                    resultSet.getLong(TABLE_COLUMN_ID),
                                    resultSet.getLong(TABLE_COLUMN_THEME_ID),
                                    resultSet.getDate(TABLE_COLUMN_DATE).toLocalDate(),
                                    resultSet.getTime(TABLE_COLUMN_START_AT).toLocalTime()
                            )
                    )
            );
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<ReservationTimeEntity> findAll() {
        String sql = "SELECT id, theme_id, date, start_at FROM reservation_time";

        return namedParameterJdbcTemplate.query(sql, resultSet -> {
            List<ReservationTimeEntity> reservationTimeEntities = new ArrayList<>();
            while (resultSet.next()) {
                reservationTimeEntities.add(
                        new ReservationTimeEntity(
                                resultSet.getLong(TABLE_COLUMN_ID),
                                resultSet.getLong(TABLE_COLUMN_THEME_ID),
                                resultSet.getDate(TABLE_COLUMN_DATE).toLocalDate(),
                                resultSet.getTime(TABLE_COLUMN_START_AT).toLocalTime()
                        )
                );
            }
            return reservationTimeEntities;
        });
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM reservation_time WHERE id = :id";

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(TABLE_COLUMN_ID, id);

        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    @Override
    public List<ReservationTimeEntity> findByDateAndThemeId(LocalDate date, Long themeId) {
        String sql = "SELECT id, theme_id, date, start_at FROM reservation_time " +
                "WHERE date = :date and theme_id = :theme_id";

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(TABLE_COLUMN_DATE, date)
                .addValue(TABLE_COLUMN_THEME_ID, themeId);

        return namedParameterJdbcTemplate.query(sql, sqlParameterSource, resultSet -> {
            List<ReservationTimeEntity> reservationTimeEntities = new ArrayList<>();
            while (resultSet.next()) {
                reservationTimeEntities.add(
                        new ReservationTimeEntity(
                                resultSet.getLong(TABLE_COLUMN_ID),
                                resultSet.getLong(TABLE_COLUMN_THEME_ID),
                                resultSet.getDate(TABLE_COLUMN_DATE).toLocalDate(),
                                resultSet.getTime(TABLE_COLUMN_START_AT).toLocalTime()
                        )
                );
            }
            return reservationTimeEntities;
        });
    }
}
