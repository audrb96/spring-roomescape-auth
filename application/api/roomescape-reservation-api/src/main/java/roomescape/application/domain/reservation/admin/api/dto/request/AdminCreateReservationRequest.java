package roomescape.application.domain.reservation.admin.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import roomescape.application.domain.reservation.admin.service.command.AdminCreateReservationCommand;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AdminCreateReservationRequest {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @NotBlank
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$", message = "날짜는 yyyy-MM-dd 형식이어야 합니다.")
    private final String date;

    @Positive
    @NotNull(message = "이 값은 필수입니다.")
    private final Long timeId;

    @Positive
    @NotNull(message = "이 값은 필수입니다.")
    private final Long themeId;

    @Positive
    @NotNull(message = "이 값은 필수입니다.")
    private final Long userId;

    public AdminCreateReservationRequest(String date, Long timeId, Long themeId, Long userId) {
        this.date = date;
        this.timeId = timeId;
        this.themeId = themeId;
        this.userId = userId;
    }

    public AdminCreateReservationCommand toCommand() {
        return new AdminCreateReservationCommand(this.userId, LocalDate.parse(this.date, DateTimeFormatter.ofPattern(DATE_FORMAT)), this.timeId, this.themeId);
    }

    public String getDate() {
        return date;
    }

    public Long getTimeId() {
        return timeId;
    }

    public Long getThemeId() {
        return themeId;
    }

    public Long getUserId() {
        return userId;
    }
}
