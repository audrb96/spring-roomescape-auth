package roomescape.application.service.component.creator;

import org.springframework.stereotype.Component;
import roomescape.application.service.component.clockholder.ClockHolder;
import roomescape.application.service.component.validator.CreateReservationValidator;
import roomescape.domain.reservation.Reservation;
import roomescape.domain.reservation.ReservationRepository;
import roomescape.domain.reservation.vo.CreateReservationTime;
import roomescape.domain.reservation.vo.ReservationId;
import roomescape.domain.reservation.vo.ReservationName;
import roomescape.domain.reservationtime.ReservationTime;
import roomescape.domain.theme.Theme;

@Component
public class ReservationCreator {

    private final CreateReservationValidator validator;
    private final ReservationRepository repository;
    private final ClockHolder clockHolder;


    public ReservationCreator(
            CreateReservationValidator validator,
            ReservationRepository repository,
            ClockHolder clockHolder
    ) {
        this.validator = validator;
        this.repository = repository;
        this.clockHolder = clockHolder;
    }

    public Reservation create(
            ReservationName reservationName,
            ReservationTime reservationTime,
            Theme theme
    ) {
        validator.validate(reservationTime);

        Reservation createdReservation = Reservation.create(
                ReservationId.empty(),
                reservationName,
                reservationTime,
                theme.getId(),
                new CreateReservationTime(clockHolder.getCurrentTime())
        );

        return repository.save(createdReservation);
    }
}
