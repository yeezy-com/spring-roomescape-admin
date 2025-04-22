package roomescape.domain.time;

import java.time.LocalTime;
import java.util.Objects;

public class ReservationTime {
    private final LocalTime startAt;

    public ReservationTime(final LocalTime startAt) {
        this.startAt = Objects.requireNonNull(startAt);
    }

    public LocalTime getStartAt() {
        return startAt;
    }
}
