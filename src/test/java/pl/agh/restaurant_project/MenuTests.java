package pl.agh.restaurant_project;


import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import pl.agh.restaurant_project.domain.Menu;
import pl.agh.restaurant_project.domain.Warehouse;
import pl.agh.restaurant_project.repository.MenuRepository;
import pl.agh.restaurant_project.repository.WarehouseRepository;
import pl.agh.restaurant_project.service.MenuService;
import pl.agh.restaurant_project.service.WarehouseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuTests {
    @Autowired
    private MenuService service;

    @MockBean
    private MenuRepository repository;

    @Test
    public void getMenuMealListSizeTests()
    {
        when(repository.findAll()).thenReturn(Stream.of(new Menu("Pierogi z jagodami", 15.5))
                .collect(Collectors.toList()));
        assertEquals(1, service.getALlMenu().size());
    }
    @Test
    public void getMenuAddMealTests()
    {
        when(repository.findAll()).thenReturn(Stream.of(new Menu("Pierogi z jagodami", 15.5))
                .collect(Collectors.toList()));
        assertEquals("Pierogi z jagodami", service.getALlMenu().get(0).getNameOfMeal());
        assertEquals(15.5, service.getALlMenu().get(0).getPrice(),0);
    }
}
