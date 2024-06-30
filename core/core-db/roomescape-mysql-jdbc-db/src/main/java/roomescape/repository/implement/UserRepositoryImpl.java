package roomescape.repository.implement;

import org.springframework.stereotype.Repository;
import roomescape.domain.user.User;
import roomescape.domain.user.UserRepository;
import roomescape.domain.user.vo.UserEmail;
import roomescape.domain.user.vo.UserId;
import roomescape.repository.UserJdbcRepository;
import roomescape.repository.entity.UserEntity;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJdbcRepository repository;

    public UserRepositoryImpl(UserJdbcRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> findByEmail(UserEmail email) {
        return repository.findByEmail(email.email())
                .map(UserEntity::toDomain);
    }

    @Override
    public Optional<User> findById(UserId id) {
        return repository.findById(id.id())
                .map(UserEntity::toDomain);
    }
}
