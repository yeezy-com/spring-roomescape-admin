package roomescape.reservation.dto;

import java.time.LocalDate;
import roomescape.reservation.domain.Reservation;
import roomescape.time.dto.ReservationTimeResponse;

public record ReservationResponse(Long id, String name, LocalDate date, ReservationTimeResponse time) {
    public static ReservationResponse toDto(final Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getName(),
                reservation.getDate(),
                new ReservationTimeResponse(
                        reservation.getTime().getId(),
                        reservation.getTime().getStartAt()
                )
        );
    }
}
