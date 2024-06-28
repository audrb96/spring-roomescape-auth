package roomescape.application.presentation.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import roomescape.application.service.command.CreateReservationCommand;

public class CreateReservationRequest {

    @NotBlank
    private final String name;

    @Positive
    @NotNull(message = "이 값은 필수입니다.")
    private final Long timeId;

    @Positive
    @NotNull(message = "이 값은 필수입니다.")
    private final Long themeId;


    public CreateReservationRequest(String name, Long timeId, Long themeId) {
        this.name = name;
        this.timeId = timeId;
        this.themeId = themeId;
    }

    public CreateReservationCommand toCreateReservationCommand() {
        return new CreateReservationCommand(name, timeId, themeId);
    }

    public String getName() {
        return name;
    }

    public Long getTimeId() {
        return timeId;
    }

    public Long getThemeId() {
        return themeId;
    }
}
