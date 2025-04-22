package roomescape.reservation.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import roomescape.reservation.dto.ReservationRequest;
import roomescape.reservation.dto.ReservationResponse;
import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationRepository;

@RestController
public class ReservationController {

    private final ReservationRepository reservations;

    public ReservationController(ReservationRepository reservations) {
        this.reservations = reservations;
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationResponse>> readReservations() {
        List<ReservationResponse> response = reservations.findAll().stream()
                .map(reservation -> new ReservationResponse(
                        reservation.getId(),
                        reservation.getName(),
                        reservation.getDate(),
                        reservation.getTime()))
                .toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponse> addReservation(@RequestBody final ReservationRequest requestDto) {
        try {
            Long id = reservations.add(new Reservation(null, requestDto.name(), requestDto.date(), requestDto.time()));
            Reservation findReservation = reservations.findById(id);

            return ResponseEntity.ok(
                    new ReservationResponse(
                            id,
                            findReservation.getName(),
                            findReservation.getDate(),
                            findReservation.getTime()
                    )
            );
        } catch (NullPointerException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") final Long id) {
        try {
            reservations.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
