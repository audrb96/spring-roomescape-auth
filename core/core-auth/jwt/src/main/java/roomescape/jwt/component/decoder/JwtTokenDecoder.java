package roomescape.jwt.component.decoder;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import roomescape.domain.user.vo.UserId;
import roomescape.jwt.JwtToken;
import roomescape.jwt.error.exception.InvalidJwtException;

@Component
public class JwtTokenDecoder {

    private final String secretKey;

    public JwtTokenDecoder(@Value("${security.jwt.token.secret-key}") String secretKey) {
        this.secretKey = secretKey;
    }

    public UserId decode(JwtToken token) {
        try {
            return new UserId(
                    Long.parseLong(
                            Jwts.parser()
                                    .setSigningKey(secretKey)
                                    .parseClaimsJws(token.token())
                                    .getBody()
                                    .getSubject()
                    )
            );
        } catch (ExpiredJwtException exception) {
            throw InvalidJwtException.expiredToken(token);
        } catch (UnsupportedJwtException exception) {
            throw InvalidJwtException.unsupportedToken(token);
        } catch (MalformedJwtException exception) {
            throw InvalidJwtException.malformedToken(token);
        } catch (SignatureException exception) {
            throw InvalidJwtException.signatureToken(token);
        } catch (IllegalArgumentException e) {
            throw InvalidJwtException.tokenClaimsStringEmpty(token);
        }
    }
}
