package roomescape.reservation.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import roomescape.reservation.domain.Reservation;
import roomescape.time.domain.ReservationTime;

public record ReservationResponse(Long id, String name, LocalDate date, ReservationTime time) {
    public static ReservationResponse toDto(final Reservation reservation) {
        return new ReservationResponse(reservation.getId(), reservation.getName(), reservation.getDate(), reservation.getTime());
    }
}
