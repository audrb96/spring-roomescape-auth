package roomescape.presentation.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import roomescape.application.presentation.api.ReservationTimeQueryApi;
import roomescape.application.presentation.api.dto.response.FindAllReservationTimesResponse;
import roomescape.application.presentation.api.dto.response.FindAvailableTimesResponse;
import roomescape.application.service.ReservationTimeService;
import roomescape.domain.reservation.vo.ReservationDate;
import roomescape.domain.reservationtime.ReservationTime;
import roomescape.domain.reservationtime.ReservationTimes;
import roomescape.domain.reservationtime.vo.ReservationTimeId;
import roomescape.domain.reservationtime.vo.ReservationTimeStartAt;
import roomescape.domain.theme.vo.ThemeId;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservationTimeQueryApi.class)
class ReservationTimeQueryApiTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private ReservationTimeService reservationTimeService;

    @Autowired
    private MockMvc mockMvc;

    private ReservationTimes reservationTimes;

    @BeforeEach
    void setUp() {
        ReservationTime reservationTime = new ReservationTime(
                new ReservationTimeId(1L),
                new ThemeId(1L),
                new ReservationDate(LocalDate.of(2024, 6, 28)),
                new ReservationTimeStartAt(LocalTime.of(16, 1))
        );

        reservationTimes = new ReservationTimes(List.of(reservationTime));
    }

    @Test
    @DisplayName("예약시간 전체 조회 API 컨트롤러 테스트")
    void findAllReservationsTest() throws Exception {
        given(reservationTimeService.findAll()).willReturn(reservationTimes);
        List<FindAllReservationTimesResponse> response = FindAllReservationTimesResponse.from(reservationTimes);

        mockMvc.perform(
                        get("/times")
                )
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(response)));
    }

    @Test
    @DisplayName("예약 가능 시간 조회 API 컨트롤러 테스트")
    void findAvailableTest() throws Exception {
        given(reservationTimeService.findAvailable(any())).willReturn(reservationTimes);
        List<FindAvailableTimesResponse> response = FindAvailableTimesResponse.from(reservationTimes);

        mockMvc.perform(
                        get("/times/available")
                                .param("date", "2024-06-28")
                                .param("themeId", "1")
                )
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(response)));
    }
}
