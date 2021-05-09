package pl.agh.restaurant_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.restaurant_project.domain.Warehouse;
import pl.agh.restaurant_project.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;


    public List<Warehouse> getAllWarehouse()
    {
        return warehouseRepository.findAll();
    }

    public Warehouse save(Warehouse product) {
        return warehouseRepository.save(product);
    }
    public void delete(long id) {
        warehouseRepository.deleteById(id);
    }
    public Warehouse get(Long id) { return warehouseRepository.findById(id).get();}

    public Warehouse update(Long ID,  Warehouse updatedWarehouse) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(ID);

        if (optionalWarehouse.isPresent()) {
            Warehouse warehouse = optionalWarehouse.get();
            warehouse.setNameOfProduct(updatedWarehouse.getNameOfProduct());
            warehouse.setAmount(updatedWarehouse.getAmount());
            warehouse.setToOrder(updatedWarehouse.getToOrder());

            return warehouseRepository.save(warehouse);
        }
        return null;
    }
}
