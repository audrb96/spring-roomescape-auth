package roomescape.application.presentation.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import roomescape.application.service.command.CreateReservationTimeCommand;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CreateReservationTimeRequest {

    private static final String TIME_FORMAT = "HH:mm";

    private final static String DATE_FORMAT = "yyyy-MM-dd";

    @NotBlank
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$", message = "날짜는 yyyy-MM-dd 형식이어야 합니다.")
    private final String date;

    @NotBlank
    @Pattern(regexp = "^(?:[01]\\d|2[0-3]):[0-5]\\d$", message = "시작시간은 HH:mm 형식이어야 합니다.")
    private final String startAt;

    public CreateReservationTimeRequest(String date, String startAt) {
        this.date = date;
        this.startAt = startAt;
    }

    public CreateReservationTimeCommand toCommand() {
        return new CreateReservationTimeCommand(
                LocalDate.parse(this.date, DateTimeFormatter.ofPattern(DATE_FORMAT)),
                LocalTime.parse(this.startAt, DateTimeFormatter.ofPattern(TIME_FORMAT))
        );
    }

    public String getDate() {
        return date;
    }

    public String getStartAt() {
        return startAt;
    }
}
