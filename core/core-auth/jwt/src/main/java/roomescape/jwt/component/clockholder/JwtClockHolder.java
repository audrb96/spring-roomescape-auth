package roomescape.jwt.component.clockholder;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtClockHolder implements ClockHolder {
    @Override
    public Date getCurrentTime() {
        return new Date();
    }
}
