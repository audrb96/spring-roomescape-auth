package roomescape.domain.user;

import roomescape.domain.user.vo.UserEmail;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(UserEmail email);
}
