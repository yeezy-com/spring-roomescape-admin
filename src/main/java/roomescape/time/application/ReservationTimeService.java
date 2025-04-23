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

        return ReservationTimeResponse.toDto(findReservationTime);
    }

    public void delete(final Long id) {
        reservationTimeRepository.deleteById(id);
    }

    public List<ReservationTimeResponse> findAll() {
        return reservationTimeRepository.findAll().stream()
                .map(ReservationTimeResponse::toDto)
                .toList();
    }
}
