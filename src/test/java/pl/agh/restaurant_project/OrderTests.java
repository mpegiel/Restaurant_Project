package pl.agh.restaurant_project;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import pl.agh.restaurant_project.domain.Order;
import pl.agh.restaurant_project.repository.OrderRepository;
import pl.agh.restaurant_project.service.OrderService;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTests {
    @Autowired
    private OrderService service;

    @MockBean
    private OrderRepository repository;

    @Test
    public void getOrdersTests()
    {
        when(repository.findAll()).thenReturn(Stream.of(new Order(1L, "1"))
                .collect(Collectors.toList()));
        assertEquals(1, service.getALlOrders().size());
    }
    @Test
    public void getOrderAddTests()
    {
        when(repository.findAll()).thenReturn(Stream.of(new Order(1L, "1"))
                .collect(Collectors.toList()));
        assertEquals("1", service.getALlOrders().get(0).getCode());
    }
}