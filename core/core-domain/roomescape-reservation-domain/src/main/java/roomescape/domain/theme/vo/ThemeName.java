package roomescape.domain.theme.vo;

import roomescape.util.StringUtils;

public record ThemeName(String name) {

    public boolean isEmpty() {
        return StringUtils.isEmpty(name);
    }
}
