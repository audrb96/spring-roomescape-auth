package roomescape.application.presentation.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import roomescape.application.presentation.api.dto.request.LoginRequest;
import roomescape.application.service.AuthService;
import roomescape.jwt.JwtToken;
import roomescape.jwt.extractor.CookieTokenExtractor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthApi.class)
class AuthApiTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private AuthService authService;

    @MockBean
    private CookieTokenExtractor tokenExtractor;


    @Autowired
    private MockMvc mockMvc;

    private JwtToken token;

    @BeforeEach
    void setUp() {
        token = new JwtToken("token1234");
    }

    @Test
    @DisplayName("로그인 API 컨트롤러 테스트")
    void loginTest() throws Exception {
        LoginRequest request = new LoginRequest("kilian@gmail.com", "1234");

        given(authService.login(any())).willReturn(token);

        mockMvc.perform(
                        post("/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(cookie().exists("token"))
                .andExpect(cookie().value("token", "token1234"));
    }

}
