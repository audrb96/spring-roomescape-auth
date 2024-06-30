package roomescape.domain.auth;

import roomescape.domain.user.vo.UserName;

public class LoginCheck {

    private final UserName userName;

    public LoginCheck(UserName userName) {
        this.userName = userName;
    }

    public UserName getUserName() {
        return userName;
    }
}
