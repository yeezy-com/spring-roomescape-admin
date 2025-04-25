package roomescape.time.domain;

import java.util.List;

public interface ReservationTimeRepository {

    Long add(final ReservationTime reservationTime);

    int deleteById(final Long id);

    List<ReservationTime> findAll();

    ReservationTime findById(Long id);
}
