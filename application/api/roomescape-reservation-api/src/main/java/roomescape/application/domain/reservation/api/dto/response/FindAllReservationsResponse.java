package roomescape.application.domain.reservation.api.dto.response;

import roomescape.domain.reservation.ReservationView;
import roomescape.domain.reservation.ReservationViews;

import java.util.List;
import java.util.stream.Collectors;

public class FindAllReservationsResponse {
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String TIME_PATTERN = "HH:mm";
    private final Long id;
    private final User user;
    private final String date;
    private final Time time;
    private final Theme theme;

    public FindAllReservationsResponse(Long id, User user, String date, Time time, Theme theme) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.time = time;
        this.theme = theme;
    }

    private static FindAllReservationsResponse from(ReservationView reservationView) {
        return new FindAllReservationsResponse(
                reservationView.getReservationId().id(),
                new User(reservationView.getUserName().name()),
                reservationView.getFormatReservationDate(DATE_PATTERN),
                new Time(
                        reservationView.getReservationTimeId().id(),
                        reservationView.getFormatReservationTimeStartAt(TIME_PATTERN)
                ),
                new Theme(
                        reservationView.getThemeId().id(), reservationView.getThemeName().name()
                )
        );
    }

    public static List<FindAllReservationsResponse> toFindAllReservationsResponses(ReservationViews reservationViews) {
        return reservationViews.getReservationViews()
                .stream()
                .map(FindAllReservationsResponse::from)
                .collect(Collectors.toList());
    }

    public Theme getTheme() {
        return theme;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    private static class Time {

        private final Long timeId;

        private final String startAt;

        public Time(Long timeId, String startAt) {
            this.timeId = timeId;
            this.startAt = startAt;
        }

        public Long getTimeId() {
            return timeId;
        }

        public String getStartAt() {
            return startAt;
        }
    }

    private static class User {

        private final String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private static class Theme {

        private final Long id;

        private final String name;

        public Theme(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
