package pl.agh.restaurant_project;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import pl.agh.restaurant_project.domain.Reservation;
import pl.agh.restaurant_project.repository.ReservationRepository;
import pl.agh.restaurant_project.service.ReservationService;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationTests {
    @Autowired
    private ReservationService service;

    @MockBean
    private ReservationRepository repository;

    @Test
    public void getReservationTests()
    {
        when(repository.findAll()).thenReturn(Stream.of(new Reservation(1, "19.05.2021 17:00", "Jan Kowalski", "123456789"))
                .collect(Collectors.toList()));
        assertEquals(1, service.getAllReservations().size());
    }
    @Test
    public void getReservationAddReservationTests()
    {
        when(repository.findAll()).thenReturn(Stream.of(new Reservation(1, "19.05.2021 17:00", "Jan Kowalski", "123456789"))
                .collect(Collectors.toList()));
        assertEquals(1, service.getAllReservations().get(0).getTableNumber());
        assertEquals("19.05.2021 17:00", service.getAllReservations().get(0).getDate());
        assertEquals("Jan Kowalski", service.getAllReservations().get(0).getPersonData());
        assertEquals("123456789", service.getAllReservations().get(0).getTelephoneNumber());
    }
}
