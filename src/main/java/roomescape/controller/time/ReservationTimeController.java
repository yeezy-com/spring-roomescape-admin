package roomescape.controller.time;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import roomescape.domain.time.ReservationTime;
import roomescape.domain.time.ReservationTimeRepository;
import roomescape.dto.ReservationTimeRequest;
import roomescape.dto.ReservationTimeResponse;

@RestController
public class ReservationTimeController {

    private final ReservationTimeRepository reservationTimeRepository;

    public ReservationTimeController(ReservationTimeRepository reservationTimeRepository) {
        this.reservationTimeRepository = reservationTimeRepository;
    }

    @PostMapping("/times")
    public ResponseEntity<ReservationTimeResponse> addTime(@RequestBody ReservationTimeRequest reservationTimeRequest) {
        Long id = reservationTimeRepository.add(new ReservationTime(null, reservationTimeRequest.startAt()));
        ReservationTime findReservationTime = reservationTimeRepository.findById(id);

        return ResponseEntity.ok(new ReservationTimeResponse(
                findReservationTime.getId(),
                findReservationTime.getStartAt()
        ));
    }

    @GetMapping("/times")
    public ResponseEntity<List<ReservationTimeResponse>> getTimes() {
        List<ReservationTimeResponse> response = reservationTimeRepository.findAll().stream()
                .map(reservationTime -> new ReservationTimeResponse(
                        reservationTime.getId(),
                        reservationTime.getStartAt())
                )
                .toList();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/times/{id}")
    public ResponseEntity<Void> deleteTime(@PathVariable("id") Long id) {
        reservationTimeRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
