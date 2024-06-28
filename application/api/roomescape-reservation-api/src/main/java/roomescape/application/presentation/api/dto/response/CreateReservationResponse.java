package roomescape.application.presentation.api.dto.response;

import roomescape.domain.reservation.Reservation;

public class CreateReservationResponse {

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    private final Long id;

    private final String name;

    private final Long timeId;

    private final Long themeId;

    public CreateReservationResponse(Long id, String name, Long timeId, Long themeId) {
        this.id = id;
        this.name = name;
        this.timeId = timeId;
        this.themeId = themeId;
    }

    public static CreateReservationResponse from(Reservation reservation) {
        return new CreateReservationResponse(
                reservation.getId(),
                reservation.getName(),
                reservation.getReservationTimeId(),
                reservation.getThemeId()
        );
    }

    public Long getId() {
        return id;
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
