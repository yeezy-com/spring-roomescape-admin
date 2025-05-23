package roomescape;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import roomescape.model.Reservation;
import roomescape.model.ReservationDateTime;
import roomescape.model.Reservations;

class ReservationsTest {

    @DisplayName("예약을 추가할 수 있다.")
    @Test
    void can_add_reservation() {
        Reservations reservations = new Reservations();
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.of(2025, 4, 18), LocalTime.of(11, 0));

        reservations.add(new Reservation("율무", new ReservationDateTime(localDateTime)));

        assertThat(reservations.getReservations()).hasSize(1);
    }

    @DisplayName("예약을 삭제할 수 있다.")
    @Test
    void can_remove_reservation() {
        Reservations reservations = new Reservations();
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.of(2025, 4, 18), LocalTime.of(11, 0));

        reservations.add(new Reservation("율무", new ReservationDateTime(localDateTime)));
        reservations.removeById(1L);

        assertThat(reservations.getReservations()).hasSize(0);
    }

    @DisplayName("존재하지 않는 예약은 삭제할 수 없다.")
    @Test
    void do_not_delete_non_exist_reservation() {
        Reservations reservations = new Reservations();

        assertThatThrownBy(() -> reservations.removeById(1L))
                .isInstanceOf(RuntimeException.class);
    }
}
