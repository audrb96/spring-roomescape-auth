package roomescape.application.presentation.api.dto.response;

import roomescape.domain.reservationtime.ReservationTime;

public class CreateReservationTimeResponse {

    private static final String DATE_PATTEN = "yyyy-MM-dd";
    private static final String START_AT_PATTEN = "HH:mm";

    private final Long id;

    private final String date;

    private final String startAt;

    public CreateReservationTimeResponse(Long id, String date, String startAt) {
        this.id = id;
        this.date = date;
        this.startAt = startAt;
    }

    public static CreateReservationTimeResponse from(ReservationTime reservationTime) {
        return new CreateReservationTimeResponse(
                reservationTime.getId().id(),
                reservationTime.getFormatDate(DATE_PATTEN),
                reservationTime.getFormatStartAt(START_AT_PATTEN)
        );
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
