package roomescape.application.presentation.api.dto.response;

import roomescape.domain.reservationtime.ReservationTime;

public class CreateReservationTimeResponse {

    private static final String DATE_PATTEN = "yyyy-MM-dd";
    private static final String START_AT_PATTEN = "HH:mm";

    private final Long id;

    private final Long themeId;

    private final String date;

    private final String startAt;

    public CreateReservationTimeResponse(Long id, Long themeId, String date, String startAt) {
        this.id = id;
        this.themeId = themeId;
        this.date = date;
        this.startAt = startAt;
    }

    public static CreateReservationTimeResponse from(ReservationTime reservationTime) {
        return new CreateReservationTimeResponse(
                reservationTime.getId(),
                reservationTime.getThemeId(),
                reservationTime.getFormatDate(DATE_PATTEN),
                reservationTime.getFormatStartAt(START_AT_PATTEN)
        );
    }

    public Long getThemeId() {
        return themeId;
    }

    public String getDate() {
        return date;
    }

    public Long getId() {
        return id;
    }

    public String getStartAt() {
        return startAt;
    }
}
