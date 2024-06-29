package roomescape.domain.theme.vo;

import roomescape.util.StringUtils;

public record ThemeThumbnail(String thumbnail) {

    public boolean isEmpty() {
        return StringUtils.isEmpty(thumbnail);
    }
}
