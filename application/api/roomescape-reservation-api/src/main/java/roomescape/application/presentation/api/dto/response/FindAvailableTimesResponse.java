package roomescape.application.presentation.api.dto.response;

import roomescape.domain.reservationtime.ReservationTime;
import roomescape.domain.reservationtime.ReservationTimes;

import java.util.List;
import java.util.stream.Collectors;

public class FindAvailableTimesResponse {

    private static final String TIME_FORMAT = "HH:mm";

    private final Long timeId;

    private final String startAt;

    public FindAvailableTimesResponse(Long timeId, String startAt) {
        this.timeId = timeId;
        this.startAt = startAt;
    }

    public static FindAvailableTimesResponse from(ReservationTime time) {
        return new FindAvailableTimesResponse(time.getId(), time.getFormatStartAt(TIME_FORMAT));
    }

    public static List<FindAvailableTimesResponse> from(ReservationTimes times) {
        return times.getReservationTimes().stream()
                .map(FindAvailableTimesResponse::from)
                .collect(Collectors.toList());
    }

    public Long getTimeId() {
        return timeId;
    }

    public String getStartAt() {
        return startAt;
    }
}
