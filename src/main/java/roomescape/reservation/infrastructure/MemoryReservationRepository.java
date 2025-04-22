package roomescape.reservation.infrastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;
import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationRepository;

@Repository
public class MemoryReservationRepository implements ReservationRepository {

    private final Map<Long, Reservation> reservations = new HashMap<>();
    private final AtomicLong id_generator = new AtomicLong(1);

    @Override
    public Long add(final Reservation reservation) {
        Long id = id_generator.getAndIncrement();

        reservations.put(id, reservation);
        return id;
    }

    @Override
    public void deleteById(final Long id) {
        Reservation reservation = findById(id);
        reservations.remove(id, reservation);
    }

    @Override
    public Reservation findById(final Long id) {
        if (reservations.containsKey(id)) {
            return reservations.get(id);
        }

        throw new IllegalArgumentException("[ERROR] 찾으려는 id 값이 없습니다.");
    }

    @Override
    public List<Reservation> findAll() {
        return reservations.values().stream()
                .toList();
    }
}
