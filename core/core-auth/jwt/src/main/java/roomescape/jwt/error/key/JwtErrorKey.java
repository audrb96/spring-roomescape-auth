package roomescape.jwt.error.key;

public class JwtErrorKey {

    private static final String MESSAGE_FORMAT = "[%s : %s] ";

    private final String name;
    private final String value;

    public JwtErrorKey(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String toMessage() {
        return String.format(MESSAGE_FORMAT, this.name, this.value);
    }
}
