package roomescape.reservation.application;

import java.util.List;
import org.springframework.stereotype.Service;
import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationRepository;
import roomescape.reservation.dto.ReservationRequest;
import roomescape.reservation.dto.ReservationResponse;
import roomescape.time.domain.ReservationTime;
import roomescape.time.domain.ReservationTimeRepository;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationTimeRepository reservationTimeRepository;

    public ReservationService(final ReservationRepository reservationRepository,
                              final ReservationTimeRepository reservationTimeRepository) {
        this.reservationRepository = reservationRepository;
        this.reservationTimeRepository = reservationTimeRepository;
    }

    public ReservationResponse add(final ReservationRequest request) {
        ReservationTime time = reservationTimeRepository.findById(request.timeId());

        Long id = reservationRepository.add(new Reservation(null, request.name(), request.date(), time));
        Reservation findReservation = reservationRepository.findById(id);

        return ReservationResponse.toDto(findReservation);
    }

    public List<ReservationResponse> findAll() {
        return reservationRepository.findAll().stream()
                .map(ReservationResponse::toDto)
                .toList();
    }

    public void delete(final Long id) {
        reservationRepository.deleteById(id);
    }
}
