package roomescape.reservation.domain;

import java.util.List;

public interface ReservationRepository {

    Long add(final Reservation reservation);

    Reservation findById(final Long id);

    int deleteById(final Long id);

    List<Reservation> findAll();
}
