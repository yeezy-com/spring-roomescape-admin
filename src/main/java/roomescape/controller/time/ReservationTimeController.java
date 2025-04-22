package roomescape.controller.time;

import java.time.LocalTime;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import roomescape.domain.time.ReservationTime;
import roomescape.domain.time.ReservationTimeRepository;

@RestController
public class ReservationTimeController {

    private final ReservationTimeRepository reservationTimeRepository;

    public ReservationTimeController(ReservationTimeRepository reservationTimeRepository) {
        this.reservationTimeRepository = reservationTimeRepository;
    }

    @PostMapping("/times")
    public ResponseEntity<ReservationTime> addTime(@RequestBody ReservationTime reservationTime) {
        Long id = reservationTimeRepository.add(reservationTime);
        ReservationTime findReservationTime = reservationTimeRepository.findById(id);

        return ResponseEntity.ok(findReservationTime);
    }

    @GetMapping("/times")
    public ResponseEntity<List<ReservationTime>> getTimes() {
        List<ReservationTime> reservations = reservationTimeRepository.findAll();

        return ResponseEntity.ok(reservations);
    }
}
