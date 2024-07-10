package roomescape.auth.service.component.reader;

import org.springframework.stereotype.Component;
import roomescape.auth.error.exception.AuthenticationException;
import roomescape.domain.user.User;
import roomescape.domain.user.UserRepository;
import roomescape.domain.user.vo.UserEmail;
import roomescape.domain.user.vo.UserId;

@Component
public class UserReader {

    private final UserRepository repository;

    public UserReader(UserRepository repository) {
        this.repository = repository;
    }

    public User readByEmail(UserEmail email) {
        return repository.findByEmail(email).orElseThrow(() -> AuthenticationException.notExistEmail(email));
    }

    public User readById(UserId id) {
        return repository.findById(id).orElseThrow(() -> AuthenticationException.notFoundUser(id));
    }
}
