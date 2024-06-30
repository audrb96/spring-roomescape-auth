package roomescape.domain.user;

import roomescape.domain.user.validator.UserValidator;
import roomescape.domain.user.vo.UserEmail;
import roomescape.domain.user.vo.UserId;
import roomescape.domain.user.vo.UserName;
import roomescape.domain.user.vo.UserPassword;

public class User {

    private final UserId id;

    private final UserName name;

    private final UserEmail email;

    private final UserPassword password;

    public User(UserId id, UserName name, UserEmail email, UserPassword password) {
        UserValidator.validate(id, name, email, password);
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserId getId() {
        return id;
    }
}
