package roomescape.domain.theme.vo;

import roomescape.util.StringUtils;

public record ThemeDescription(String description) {

    public boolean isEmpty() {
        return StringUtils.isEmpty(description);
    }
}
