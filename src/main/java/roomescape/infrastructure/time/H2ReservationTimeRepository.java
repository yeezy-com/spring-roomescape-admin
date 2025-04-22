package roomescape.infrastructure.time;

import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import roomescape.domain.time.ReservationTime;
import roomescape.domain.time.ReservationTimeRepository;

@Repository
public class H2ReservationTimeRepository implements ReservationTimeRepository {

    private final JdbcTemplate jdbcTemplate;

    public H2ReservationTimeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long add(final ReservationTime reservationTime) {
        String sql = "INSERT INTO reservation_time (start_at) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});

            preparedStatement.setTime(1, Time.valueOf(reservationTime.getStartAt()));
            return preparedStatement;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public void deleteById(final Long id) {

    }

    @Override
    public List<ReservationTime> findAll() {
        return List.of();
    }
}
