package roomescape.reservation.infrastructure;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationRepository;

@Repository
@Primary
public class H2ReservationRepository implements ReservationRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Reservation> rowMapper = (resultSet, rowNum) -> new Reservation(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getDate("date").toLocalDate(),
                resultSet.getTime("time").toLocalTime()
    );

    public H2ReservationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long add(final Reservation reservation) {
        String sql = "INSERT INTO reservation (name, date, time) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});

            preparedStatement.setString(1, reservation.getName());
            preparedStatement.setDate(2, Date.valueOf(reservation.getDate()));
            preparedStatement.setTime(3, Time.valueOf(reservation.getTime()));

            return preparedStatement;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public Reservation findById(final Long id) {
        String sql = "SELECT * FROM reservation WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public void deleteById(final Long id) {
        String sql = "DELETE FROM reservation WHERE id = ?";
        int deleteCount = jdbcTemplate.update(sql, id);

        validateIsExistsId(deleteCount);
    }

    private void validateIsExistsId(int deleteCount) {
        if (deleteCount == 0) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 예약입니다.");
        }
    }

    @Override
    public List<Reservation> findAll() {
        String sql = "SELECT * FROM reservation";

        return jdbcTemplate.query(sql, rowMapper);
    }
}
