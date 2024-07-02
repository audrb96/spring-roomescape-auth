package roomescape.repository;

import roomescape.repository.entity.UserEntity;

import java.util.Optional;

public interface UserJdbcRepository {

    UserEntity save(UserEntity entity);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findById(Long id);
}
