package roomescape.reservation.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import roomescape.time.domain.ReservationTime;

public record ReservationResponse(Long id, String name, LocalDate date, ReservationTime time) {
}
