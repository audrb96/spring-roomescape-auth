package roomescape.application.presentation.api.dto.request;

import roomescape.application.service.command.CreateUserCommand;

public class CreateUserRequest {

    private final String email;

    private final String password;

    private final String name;

    public CreateUserRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public CreateUserCommand toCommand() {
        return new CreateUserCommand(this.email, this.password, this.name);
    }
}
