package roomescape.entity.reservation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import roomescape.model.Reservation;
import roomescape.model.ReservationDateTime;
import roomescape.model.ReservationRepository;

@Repository
@Primary
public class H2ReservationRepository implements ReservationRepository {

    private final JdbcTemplate jdbcTemplate;

    public H2ReservationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long add(final Reservation reservation) {
        return null;
    }

    @Override
    public Reservation findById(final Long id) {
        return null;
    }

    @Override
    public void deleteById(final Long id) {
    }

    @Override
    public List<Reservation> findAll() {
        return null;
    }
}
