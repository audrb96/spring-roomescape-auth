package roomescape.application.domain.reservation.admin.api.dto.response;

import roomescape.domain.reservation.Reservation;

public class AdminCreateReservationResponse {

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    private final Long id;

    private final Long userId;

    private final String date;

    private final Long timeId;

    private final Long themeId;

    public AdminCreateReservationResponse(Long id, Long userId, String date, Long timeId, Long themeId) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.timeId = timeId;
        this.themeId = themeId;
    }

    public static AdminCreateReservationResponse from(Reservation reservation) {
        return new AdminCreateReservationResponse(
                reservation.getId().id(),
                reservation.getUserId().id(),
                reservation.getFormatDate(DATE_PATTERN),
                reservation.getTimeId().id(),
                reservation.getThemeId().id()
        );
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
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
}
