package roomescape.application.service.command;

import roomescape.domain.user.vo.UserEmail;

public class LoginCommand {

    private final String email;

    private final String password;

    public LoginCommand(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserEmail toUserEmail() {
        return new UserEmail(this.email);
    }
}
