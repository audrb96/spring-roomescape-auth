package roomescape.application.service;

import org.springframework.stereotype.Service;
import roomescape.application.service.command.CreateUserCommand;
import roomescape.auth.password.encoder.UserPasswordEncoder;
import roomescape.domain.user.User;
import roomescape.domain.user.UserRepository;
import roomescape.domain.user.vo.UserPassword;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(CreateUserCommand command) {
        User user = command.toUser();
        UserPassword encodedPassword = passwordEncoder.encode(user.getPassword());

        return userRepository.save(user.withPassword(encodedPassword));
    }
}
