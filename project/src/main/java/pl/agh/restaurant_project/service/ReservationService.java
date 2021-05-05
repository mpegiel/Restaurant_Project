package pl.agh.restaurant_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.restaurant_project.domain.Reservation;
import pl.agh.restaurant_project.repository.ReservationRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {

        return reservationRepository.findAll();
    }

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
    public void delete(long id) {
        reservationRepository.deleteById(id);
    }
    public Reservation get(long id) {
        return reservationRepository.findById(id).get();
    }
    public Reservation update(long id,  Reservation updatedReservation) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);

        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.setTableNumber(updatedReservation.getTableNumber());
            reservation.setDate(updatedReservation.getDate());
            reservation.setTelephoneNumber(updatedReservation.getTelephoneNumber());
            reservation.setPersonData(updatedReservation.getPersonData());

            return reservationRepository.save(reservation);
        }
        return null;
    }

}