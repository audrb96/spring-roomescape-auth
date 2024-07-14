package roomescape.jwt.component.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import roomescape.domain.user.vo.UserId;
import roomescape.jwt.JwtToken;
import roomescape.jwt.component.clockholder.ClockHolder;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final ClockHolder clockHolder;
    @Value("${security.jwt.token.secret-key}")
    private final String secretKey;
    @Value("${security.jwt.token.expire-length}")
    private final long validityInMilliseconds;

    public JwtTokenProvider(
            ClockHolder clockHolder,
            @Value("${security.jwt.token.secret-key}") String secretKey,
            @Value("${security.jwt.token.expire-length}") long validityInMilliseconds
    ) {
        this.clockHolder = clockHolder;
        this.secretKey = secretKey;
        this.validityInMilliseconds = validityInMilliseconds;
    }

    public JwtToken provide(UserId userId) {
        Claims claims = Jwts.claims().setSubject(userId.id().toString());
        Date now = clockHolder.getCurrentTime();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return new JwtToken(
                Jwts.builder()
                        .setClaims(claims)
                        .setIssuedAt(now)
                        .setExpiration(validity)
                        .signWith(SignatureAlgorithm.HS256, secretKey)
                        .compact()
        );
    }
}
