package roomescape.repository.mysql;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import roomescape.repository.UserJdbcRepository;
import roomescape.repository.entity.UserEntity;

import java.util.Optional;

@Component
public class MySQLJdbcUserRepository implements UserJdbcRepository {

    private static final String TABLE_COLUMN_ID = "id";
    private static final String TABLE_COLUMN_NAME = "name";
    private static final String TABLE_COLUMN_EMAIL = "email";
    private static final String TABLE_COLUMN_PASSWORD = "password";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MySQLJdbcUserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        String sql = "SELECT id, name, email, password FROM users WHERE email = :email";

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(TABLE_COLUMN_EMAIL, email);

        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(
                            sql,
                            sqlParameterSource,
                            (resultSet, rowNum) -> new UserEntity(
                                    resultSet.getLong(TABLE_COLUMN_ID),
                                    resultSet.getString(TABLE_COLUMN_NAME),
                                    resultSet.getString(TABLE_COLUMN_EMAIL),
                                    resultSet.getString(TABLE_COLUMN_PASSWORD)
                            )
                    )
            );
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        String sql = "SELECT id, name, email, password FROM users WHERE id = :id";

        MapSqlParameterSource sqlParameterource = new MapSqlParameterSource()
                .addValue(TABLE_COLUMN_ID, id);

        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(
                            sql,
                            sqlParameterource,
                            (resultSet, rowNum) -> new UserEntity(
                                    resultSet.getLong(TABLE_COLUMN_ID),
                                    resultSet.getString(TABLE_COLUMN_NAME),
                                    resultSet.getString(TABLE_COLUMN_EMAIL),
                                    resultSet.getString(TABLE_COLUMN_PASSWORD)
                            )
                    )
            );
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }
}
