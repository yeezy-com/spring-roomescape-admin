package roomescape.time.application;

import java.util.List;
import org.springframework.stereotype.Service;
import roomescape.time.domain.ReservationTime;
import roomescape.time.domain.ReservationTimeRepository;
import roomescape.time.dto.ReservationTimeRequest;
import roomescape.time.dto.ReservationTimeResponse;

@Service
public class ReservationTimeService {

    private final ReservationTimeRepository reservationTimeRepository;

    public ReservationTimeService(final ReservationTimeRepository reservationTimeRepository) {
        this.reservationTimeRepository = reservationTimeRepository;
    }

    public ReservationTimeResponse add(final ReservationTimeRequest request) {
        Long id = reservationTimeRepository.add(new ReservationTime(null, request.startAt()));
        ReservationTime findReservationTime = reservationTimeRepository.findById(id);

        return ReservationTimeResponse.from(findReservationTime);
    }

    public void delete(final Long id) {
        int count = reservationTimeRepository.deleteById(id);
        validateIsExistsReservationTimeId(count);
    }

    private void validateIsExistsReservationTimeId(final int count) {
        if (count == 0) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 예약 시간입니다.");
        }
    }

    public List<ReservationTimeResponse> findAll() {
        return reservationTimeRepository.findAll().stream()
                .map(ReservationTimeResponse::from)
                .toList();
    }
}
