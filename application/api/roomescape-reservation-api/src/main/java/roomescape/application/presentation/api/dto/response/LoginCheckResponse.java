package roomescape.application.presentation.api.dto.response;


import roomescape.domain.auth.LoginCheck;

public class LoginCheckResponse {

    private final String name;

    public LoginCheckResponse(String name) {
        this.name = name;
    }

    public static LoginCheckResponse from(LoginCheck loginCheck) {
        return new LoginCheckResponse(loginCheck.getUserName().name());
    }

    public String getName() {
        return name;
    }
}
