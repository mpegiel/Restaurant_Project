package pl.agh.restaurant_project.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import pl.agh.restaurant_project.domain.AvailabilityEvent;

import java.time.LocalDateTime;
import java.util.List;

public interface AvailabilityEventRepository extends CrudRepository<AvailabilityEvent, Long> {
    @Query("from AvailabilityEvent e where not(e.end < :from and e.start > :to)")
    public List<AvailabilityEvent> findBetween(@Param("from") LocalDateTime start, @Param("to") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime end);
}