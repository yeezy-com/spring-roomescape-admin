package roomescape.reservation.infrastructure;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationRepository;
import roomescape.time.domain.ReservationTime;

@Repository
@Primary
public class H2ReservationRepository implements ReservationRepository {


    private static final RowMapper<Reservation> ROW_MAPPER = (resultSet, rowNum) -> new Reservation(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getDate("date").toLocalDate(),
            new ReservationTime(
                    resultSet.getLong("time_id"),
                    resultSet.getTime("start_at").toLocalTime()
            )
    );

    private final JdbcTemplate jdbcTemplate;

    public H2ReservationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long add(final Reservation reservation) {
        String sql = "INSERT INTO reservation (name, date, time_id) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});

            preparedStatement.setString(1, reservation.getName());
            preparedStatement.setDate(2, Date.valueOf(reservation.getDate()));
            preparedStatement.setLong(3, reservation.getTime().getId());

            return preparedStatement;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public Reservation findById(final Long id) {
        String sql = """               
                SELECT
                    r.id as reservation_id,
                    r.name,
                    r.date,
                    t.id as time_id,
                    t.start_at as time_value
                FROM reservation as r
                INNER JOIN reservation_time as t
                ON r.time_id = t.id
                WHERE r.id = ?
                """;
        return jdbcTemplate.queryForObject(sql, ROW_MAPPER, id);
    }

    @Override
    public int deleteById(final Long id) {
        String sql = "DELETE FROM reservation WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Reservation> findAll() {
        String sql = """               
                SELECT
                    r.id as reservation_id,
                    r.name,
                    r.date,
                    t.id as time_id,
                    t.start_at as time_value
                FROM reservation as r
                INNER JOIN reservation_time as t
                ON r.time_id = t.id
                """;

        return jdbcTemplate.query(sql, ROW_MAPPER);
    }
}
