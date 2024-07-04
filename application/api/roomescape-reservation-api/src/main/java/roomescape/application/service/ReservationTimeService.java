package roomescape.application.service;

import org.springframework.stereotype.Service;
import roomescape.application.service.command.CreateReservationTimeCommand;
import roomescape.application.service.command.DeleteReservationTimeCommand;
import roomescape.application.service.component.creator.ReservationTimeCreator;
import roomescape.application.service.component.reader.ReservationTimeReader;
import roomescape.application.service.component.remover.ReservationTimeRemover;
import roomescape.application.service.query.FindAvailableTimesQuery;
import roomescape.domain.reservationtime.ReservationTime;
import roomescape.domain.reservationtime.ReservationTimeRepository;
import roomescape.domain.reservationtime.ReservationTimes;

@Service
public class ReservationTimeService {

    private final ReservationTimeReader reservationTimeReader;
    private final ReservationTimeCreator reservationTimeCreator;
    private final ReservationTimeRemover reservationTimeRemover;

    public ReservationTimeService(
            ReservationTimeRepository reservationTimeRepository,
            ReservationTimeReader reservationTimeReader,
            ReservationTimeCreator reservationTimeCreator,
            ReservationTimeRemover reservationTimeRemover
    ) {
        this.reservationTimeReader = reservationTimeReader;
        this.reservationTimeCreator = reservationTimeCreator;
        this.reservationTimeRemover = reservationTimeRemover;
    }

    public ReservationTime create(CreateReservationTimeCommand command) {
        return reservationTimeCreator.create(command.toReservationTime());
    }

    public ReservationTimes findAll() {
        return reservationTimeReader.readAll();
    }

    public void delete(DeleteReservationTimeCommand command) {
        reservationTimeRemover.remove(command.getReservationTimeId());
    }

    public ReservationTimes findAvailable(FindAvailableTimesQuery query) {
        return reservationTimeReader.readByDateAndThemeId(query.fetchReservationDate(), query.fetchThemeId());
    }
}
