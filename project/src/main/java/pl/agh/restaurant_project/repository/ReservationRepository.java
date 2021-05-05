package pl.agh.restaurant_project.repository;

import java.beans.JavaBean;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.agh.restaurant_project.domain.Menu;
import pl.agh.restaurant_project.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}
