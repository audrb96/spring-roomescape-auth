package roomescape.auth.service.query;

import roomescape.domain.user.vo.UserId;

public class LoginCheckQuery {

    private final UserId userId;


    public LoginCheckQuery(UserId userId) {
        this.userId = userId;
    }

    public UserId getUserId() {
        return userId;
    }
}
