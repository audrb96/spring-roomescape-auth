package roomescape.application.presentation.api;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import roomescape.application.presentation.api.dto.request.FindAvailableTimesRequest;
import roomescape.application.presentation.api.dto.response.FindAllReservationTimesResponse;
import roomescape.application.presentation.api.dto.response.FindAvailableTimesResponse;
import roomescape.application.service.ReservationTimeService;
import roomescape.domain.reservationtime.ReservationTimes;

import java.util.List;

@RestController
public class ReservationTimeQueryApi {

    private final ReservationTimeService reservationTimeService;

    public ReservationTimeQueryApi(ReservationTimeService reservationTimeService) {
        this.reservationTimeService = reservationTimeService;
    }

    @GetMapping("/times")
    public ResponseEntity<List<FindAllReservationTimesResponse>> findAll() {
        ReservationTimes reservationTimes = reservationTimeService.findAll();

        return ResponseEntity.ok(FindAllReservationTimesResponse.from(reservationTimes));
    }

    @GetMapping("/times/available?date={date}&themeId={themeId}")
    public ResponseEntity<List<FindAvailableTimesResponse>> findAvailableTimes(
            @Valid FindAvailableTimesRequest request
    ) {
        ReservationTimes times = reservationTimeService.findAvailableTimes(request.toQuery());

        return ResponseEntity.ok(FindAvailableTimesResponse.from(times));
    }
}
