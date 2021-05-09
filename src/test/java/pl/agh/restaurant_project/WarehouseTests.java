package pl.agh.restaurant_project;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import pl.agh.restaurant_project.domain.Warehouse;
import pl.agh.restaurant_project.repository.WarehouseRepository;
import pl.agh.restaurant_project.service.WarehouseService;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WarehouseTests {
    @Autowired
    private WarehouseService service;

    @MockBean
    private WarehouseRepository repository;

    @Test
    public void getWarehousesTests()
    {
        when(repository.findAll()).thenReturn(Stream.of(new Warehouse("Mleko", 10, true))
                .collect(Collectors.toList()));
        assertEquals(1, service.getAllWarehouse().size());
    }
}
