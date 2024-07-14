package roomescape.jwt.error.key;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JwtErrorKeys {

    private final List<JwtErrorKey> keys;

    public JwtErrorKeys(List<JwtErrorKey> keys) {
        this.keys = keys;
    }

    public static JwtErrorKeys of(JwtErrorKey... keys) {
        return new JwtErrorKeys(new ArrayList<>(Arrays.asList(keys)));
    }

    public String toMessage() {
        return keys.stream()
                .map(JwtErrorKey::toMessage)
                .collect(Collectors.joining(", "));
    }
}
