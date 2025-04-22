package roomescape.reservation.domain;

import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationDateTime;

class ReservationTest {

    @DisplayName("예약자 이름에 빈 값 혹은 null이 올 수 없다.")
    @ParameterizedTest
    @NullAndEmptySource
    void reservation_name_is_not_blank(final String name) {
        Assertions.assertThatThrownBy(() -> new Reservation(null, name,
                new ReservationDateTime(LocalDateTime.of(2020, 10, 1, 13, 0))));
    }
}
