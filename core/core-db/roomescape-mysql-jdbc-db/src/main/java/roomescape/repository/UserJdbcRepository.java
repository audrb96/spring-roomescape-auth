package roomescape.repository;

import roomescape.repository.entity.UserEntity;

import java.util.Optional;

public interface UserJdbcRepository {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findById(Long id);
}
