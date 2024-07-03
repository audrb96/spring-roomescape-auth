package roomescape.reservation.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import roomescape.domain.reservation.validator.CreateReservationValidator;
import roomescape.domain.reservation.vo.CreateReservationTime;
import roomescape.domain.reservation.vo.ReservationDate;
import roomescape.domain.reservationtime.ReservationTime;
import roomescape.domain.reservationtime.vo.ReservationTimeId;
import roomescape.domain.reservationtime.vo.ReservationTimeStartAt;
import roomescape.error.exception.CreateReservationValidateException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

class CreateReservationValidatorTest {

    @Nested
    @DisplayName("validate 함수 호출 시")
    class validate {

        @Nested
        @DisplayName("예약 시간이 현재 시간 이후라면")
        class reservationTimeIsAfterNow {

            @Test
            @DisplayName("예외가 발생하지 않는다.")
            void NotThrownException() {
                CreateReservationValidator validator = new CreateReservationValidator(
                        new ReservationTime(
                                new ReservationTimeId(1L),
                                new ReservationDate(LocalDate.of(2024, 6, 17)),
                                new ReservationTimeStartAt(LocalTime.of(18, 0))
                        ),
                        new CreateReservationTime(LocalDateTime.of(2024, 6, 17, 17, 59))
                );

                Assertions.assertDoesNotThrow(validator::validate);
            }
        }

        @Nested
        @DisplayName("예약 시간이 현재 시간 이전이라면")
        class reservationTimeIsBeforeNow {

            @Test
            @DisplayName("CreateReservationValidateException이 발생한다.")
            void throwCreateReservationValidateException() {

                CreateReservationValidator validator = new CreateReservationValidator(
                        new ReservationTime(
                                new ReservationTimeId(1L),
                                new ReservationDate(LocalDate.of(2024, 6, 17)),
                                new ReservationTimeStartAt(LocalTime.of(18, 0))
                        ),
                        new CreateReservationTime(LocalDateTime.of(2024, 6, 17, 18, 1))
                );

                Assertions.assertThrows(
                        CreateReservationValidateException.class,
                        validator::validate
                );
            }
        }
    }

}
