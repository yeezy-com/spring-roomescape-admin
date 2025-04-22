package roomescape.domain.time;

import java.time.LocalTime;
import java.util.Objects;

public class Time {
    private final LocalTime startAt;

    public Time(final LocalTime startAt) {
        this.startAt = Objects.requireNonNull(startAt);
    }

    public LocalTime getStartAt() {
        return startAt;
    }
}
