package roomescape.application.service.query;

import java.time.LocalDate;

public class FindAvailableTimesQuery {

    private final LocalDate date;

    private final Long themeId;

    public FindAvailableTimesQuery(LocalDate date, Long themeId) {
        this.date = date;
        this.themeId = themeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getThemeId() {
        return themeId;
    }
}
