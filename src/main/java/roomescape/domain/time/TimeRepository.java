package roomescape.domain.time;

import java.util.List;

public interface TimeRepository {

    Long add(final Time time);

    void deleteById(final Long id);

    List<Time> findAll();
}
