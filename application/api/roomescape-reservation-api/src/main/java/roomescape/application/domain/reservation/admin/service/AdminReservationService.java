package roomescape.application.domain.reservation.admin.service;

import org.springframework.stereotype.Service;
import roomescape.application.domain.reservation.admin.service.command.AdminCreateReservationCommand;
import roomescape.application.domain.reservation.service.component.creator.ReservationCreator;
import roomescape.application.domain.reservationtime.service.component.reader.ReservationTimeReader;
import roomescape.application.domain.theme.service.component.reader.ThemeReader;
import roomescape.domain.reservation.Reservation;
import roomescape.domain.reservationtime.ReservationTime;
import roomescape.domain.theme.Theme;

@Service
public class AdminReservationService {

    private final ReservationTimeReader reservationTimeReader;
    private final ThemeReader themeReader;
    private final ReservationCreator reservationCreator;

    public AdminReservationService(
            ReservationTimeReader reservationTimeReader,
            ThemeReader themeReader,
            ReservationCreator reservationCreator
    ) {
        this.reservationTimeReader = reservationTimeReader;
        this.themeReader = themeReader;
        this.reservationCreator = reservationCreator;
    }

    public Reservation create(AdminCreateReservationCommand command) {
        ReservationTime reservationTime = reservationTimeReader.readById(command.fetchReservationTimeId());
        Theme theme = themeReader.readById(command.fetchThemeId());

        return reservationCreator.create(command.fetchUserId(), command.fetchReservationDate(), reservationTime, theme);
    }
}
