package roomescape.application.service.component.validator;

import org.springframework.stereotype.Component;
import roomescape.application.error.exception.CreateReservationTimeValidateException;
import roomescape.domain.reservationtime.ReservationTime;
import roomescape.domain.reservationtime.ReservationTimeRepository;
import roomescape.domain.theme.ThemeRepository;

@Component
public class CreateReservationTimeValidator {

    private final ReservationTimeRepository timeRepository;

    private final ThemeRepository themeRepository;

    public CreateReservationTimeValidator(
            ReservationTimeRepository timeRepository,
            ThemeRepository themeRepository) {
        this.timeRepository = timeRepository;
        this.themeRepository = themeRepository;
    }

    public void validate(ReservationTime reservationTime) {
        if (!themeRepository.existById(reservationTime.getThemeId())) {
            throw CreateReservationTimeValidateException.notExistTheme(reservationTime.getThemeId());
        }

        if (timeRepository.existEquals(reservationTime)) {
            throw CreateReservationTimeValidateException.existEquals(reservationTime);
        }
    }
}
