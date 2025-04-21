package roomescape.entity.reservation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;
import roomescape.model.Reservation;
import roomescape.model.ReservationRepository;

@Repository
public class MemoryReservationRepository implements ReservationRepository {

    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);

    private final Map<Long, Reservation> reservations = new HashMap<>();

    @Override
    public Long add(final Reservation reservation) {
        Long id = ID_GENERATOR.getAndIncrement();

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
