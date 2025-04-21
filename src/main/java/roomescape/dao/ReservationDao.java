package roomescape.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import roomescape.dto.ReservationResponse;

@Component
public class ReservationDao {

    private final JdbcTemplate jdbcTemplate;

    public ReservationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ReservationResponse> findAll() {
        String sql = "SELECT * FROM reservation";

        List<ReservationResponse> reservations = jdbcTemplate.query(sql,
                (resultSet, rowNum) -> new ReservationResponse(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("date").toLocalDate(),
                        resultSet.getTime("time").toLocalTime()
                )
        );

        return reservations;
    }
}
