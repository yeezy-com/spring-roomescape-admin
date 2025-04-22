package roomescape.controller.time;

import java.time.LocalTime;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import roomescape.domain.time.ReservationTime;

@RestController
public class ReservationTimeController {

    @PostMapping("/times")
    public ResponseEntity<ReservationTime> addTime(@RequestBody ReservationTime reservationTime) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/times")
    public ResponseEntity<List<ReservationTime>> getTimes() {
        return ResponseEntity.ok()
                .body(List.of(new ReservationTime(LocalTime.now())));
    }
}
