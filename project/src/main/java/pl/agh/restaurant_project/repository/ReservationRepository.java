package pl.agh.restaurant_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.agh.restaurant_project.domain.Reservation;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
