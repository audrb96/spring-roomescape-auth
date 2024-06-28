package roomescape.application.service.component.validator;

import org.springframework.stereotype.Component;
import roomescape.application.error.exception.CreateReservationValidateException;
import roomescape.domain.reservation.ReservationRepository;
import roomescape.domain.reservationtime.ReservationTime;

@Component
public class CreateReservationValidator {

    private final ReservationRepository repository;

    public CreateReservationValidator(ReservationRepository repository) {
        this.repository = repository;
    }

    public void validate(ReservationTime reservationTime) {
        if (repository.existByTimeId(reservationTime.getId())) {
            throw CreateReservationValidateException.existTime(reservationTime);
        }
    }
}
