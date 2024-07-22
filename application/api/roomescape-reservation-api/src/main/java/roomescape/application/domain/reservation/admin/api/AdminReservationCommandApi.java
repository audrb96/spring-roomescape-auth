package roomescape.application.domain.reservation.admin.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import roomescape.application.domain.reservation.admin.api.dto.request.AdminCreateReservationRequest;
import roomescape.application.domain.reservation.admin.api.dto.response.AdminCreateReservationResponse;
import roomescape.application.domain.reservation.admin.service.AdminReservationService;
import roomescape.domain.reservation.Reservation;

import java.net.URI;

@RestController
public class AdminReservationCommandApi {

    private final AdminReservationService service;

    public AdminReservationCommandApi(AdminReservationService service) {
        this.service = service;
    }

    @PostMapping("/admin/reservations")
    public ResponseEntity<AdminCreateReservationResponse> create(@RequestBody AdminCreateReservationRequest request) {
        Reservation reservation = service.create(request.toCommand());

        return ResponseEntity.created(URI.create(String.format("/reservations/%d", reservation.getId().id())))
                .body(AdminCreateReservationResponse.from(reservation));
    }
}
