package pl.agh.restaurant_project;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import pl.agh.restaurant_project.domain.User;
import pl.agh.restaurant_project.repository.UserRepository;
import pl.agh.restaurant_project.service.UserService;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {
    @Autowired
    private UserService service;

    @MockBean
    private UserRepository repository;

    @Test
    public void getUsersTests()
    {
        when(repository.findAll()).thenReturn(Stream.of(new User(1L,"admin","1234","john@gmail.com","John","Doe","2000","admin"))
                .collect(Collectors.toList()));
        assertEquals(1, service.listAll().size());
    }
    @Test
    public void getUserAddTests()
    {
        when(repository.findAll()).thenReturn(Stream.of(new User(1L,"admin","1234","john@gmail.com","John","Doe","2000","admin"))
                .collect(Collectors.toList()));
        assertEquals("admin", service.listAll().get(0).getUsername());
        assertEquals("john@gmail.com", service.listAll().get(0).getPersonEmail());
        assertEquals("John", service.listAll().get(0).getFirstName());
        assertEquals("Doe", service.listAll().get(0).getPersonSurname());
        assertEquals("2000", service.listAll().get(0).getPersonSalary());
        assertEquals("admin", service.listAll().get(0).getRoles());
    }
}
