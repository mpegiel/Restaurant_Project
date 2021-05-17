package pl.agh.restaurant_project.repository;

import pl.agh.restaurant_project.domain.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {
    @Query("from Event e where not(e.endtime < :from and e.starttime > :to)")
    public List<Event> findBetween(@Param("from") LocalDateTime starttime, @Param("to") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime endtime);

    List<Event> findByJoin(Long joinId);
}