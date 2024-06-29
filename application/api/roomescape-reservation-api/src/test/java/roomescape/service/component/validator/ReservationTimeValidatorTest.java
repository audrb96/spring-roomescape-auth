package roomescape.service.component.validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import roomescape.application.error.exception.CreateReservationTimeValidateException;
import roomescape.application.service.component.validator.CreateReservationTimeValidator;
import roomescape.domain.reservation.vo.ReservationDate;
import roomescape.domain.reservationtime.ReservationTime;
import roomescape.domain.reservationtime.ReservationTimeRepository;
import roomescape.domain.reservationtime.vo.ReservationTimeId;
import roomescape.domain.reservationtime.vo.ReservationTimeStartAt;
import roomescape.domain.theme.ThemeRepository;
import roomescape.domain.theme.vo.ThemeId;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static roomescape.application.error.code.ApplicationErrorCode.CANNOT_CREATE_EXIST_RESERVATION_TIME;
import static roomescape.application.error.code.ApplicationErrorCode.CANNOT_CREATE_RESERVATION_TIME_NOT_EXIST_THEME;

@ExtendWith(MockitoExtension.class)
class ReservationTimeValidatorTest {

    @InjectMocks
    private CreateReservationTimeValidator validator;

    @Mock
    private ReservationTimeRepository reservationTimeRepository;

    @Mock
    private ThemeRepository themeRepository;

    @Test
    @DisplayName("모든 값이 같은 예약 시간은 생성할 수 없다.")
    void CannotCreateExistStartAt() {

        given(themeRepository.existById(any())).willReturn(true);
        given(reservationTimeRepository.existEquals(any())).willReturn(true);

        CreateReservationTimeValidateException exception = assertThrows(
                CreateReservationTimeValidateException.class,
                () -> validator.validate(
                        new ReservationTime(
                                new ReservationTimeId(1L),
                                new ThemeId(1L),
                                new ReservationDate(LocalDate.of(2024, 6, 8)),
                                new ReservationTimeStartAt(LocalTime.of(12, 55))
                        )
                ));
        Assertions.assertThat(exception.getCode()).isEqualTo(CANNOT_CREATE_EXIST_RESERVATION_TIME);
    }

    @Test
    @DisplayName("테마가 존재하지 않으면 생성할 수 없다.")
    void CannotCreateNotExistTheme() {

        given(themeRepository.existById(any())).willReturn(false);

        CreateReservationTimeValidateException exception = assertThrows(
                CreateReservationTimeValidateException.class,
                () -> validator.validate(
                        new ReservationTime(
                                new ReservationTimeId(1L),
                                new ThemeId(1L),
                                new ReservationDate(LocalDate.of(2024, 6, 8)),
                                new ReservationTimeStartAt(LocalTime.of(12, 55))
                        )
                ));

        Assertions.assertThat(exception.getCode()).isEqualTo(CANNOT_CREATE_RESERVATION_TIME_NOT_EXIST_THEME);
    }
}
