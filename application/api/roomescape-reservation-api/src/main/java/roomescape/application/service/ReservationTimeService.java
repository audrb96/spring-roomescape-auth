package roomescape.application.service;

import org.springframework.stereotype.Service;
import roomescape.application.service.command.CreateReservationTimeCommand;
import roomescape.application.service.command.DeleteReservationTimeCommand;
import roomescape.application.service.component.creator.ReservationTimeCreator;
import roomescape.application.service.component.remover.ReservationTimeRemover;
import roomescape.application.service.query.FindAvailableTimesQuery;
import roomescape.domain.reservation.ReservationRepository;
import roomescape.domain.reservationtime.ReservationTime;
import roomescape.domain.reservationtime.ReservationTimeRepository;
import roomescape.domain.reservationtime.ReservationTimes;

@Service
public class ReservationTimeService {

    private final ReservationTimeRepository reservationTimeRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationTimeCreator reservationTimeCreator;
    private final ReservationTimeRemover reservationTimeRemover;

    public ReservationTimeService(
            ReservationTimeRepository reservationTimeRepository,
            ReservationRepository reservationRepository,
            ReservationTimeCreator reservationTimeCreator,
            ReservationTimeRemover reservationTimeRemover
    ) {
        this.reservationTimeRepository = reservationTimeRepository;
        this.reservationRepository = reservationRepository;
        this.reservationTimeCreator = reservationTimeCreator;
        this.reservationTimeRemover = reservationTimeRemover;
    }

    public ReservationTime create(CreateReservationTimeCommand command) {
        return reservationTimeCreator.create(command.toReservationTime());
    }

    public ReservationTimes findAll() {
        return reservationTimeRepository.findAll();
    }

    public void delete(DeleteReservationTimeCommand command) {
        reservationTimeRemover.remove(command.getReservationTimeId());
    }

    public ReservationTimes findAvailable(FindAvailableTimesQuery query) {

        return null;
    }
}
