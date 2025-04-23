package roomescape.time.dto;

import java.time.LocalTime;
import roomescape.time.domain.ReservationTime;

public record ReservationTimeResponse(Long id, LocalTime startAt) {
    public static ReservationTimeResponse toDto(final ReservationTime findReservationTime) {
        return new ReservationTimeResponse(findReservationTime.getId(), findReservationTime.getStartAt());
    }
}
