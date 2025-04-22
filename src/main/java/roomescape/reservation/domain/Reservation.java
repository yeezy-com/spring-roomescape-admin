package roomescape.reservation.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {

    private final Long id;
    private final String name;
    private final ReservationDateTime reservationDateTime;

    public Reservation(Long id, String name, ReservationDateTime reservationDateTime) {
        this.id = id;
        this.name = validateNonBlank(name);
        this.reservationDateTime = reservationDateTime;
    }

    private String validateNonBlank(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름은 빈 값일 수 없습니다.");
        }

        return name;
    }

    public Long getId() { return id; }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return reservationDateTime.getDate();
    }

    public LocalTime getTime() {
        return reservationDateTime.getTime();
    }
}
