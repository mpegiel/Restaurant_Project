package pl.agh.restaurant_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.agh.restaurant_project.domain.Menu;
import pl.agh.restaurant_project.domain.Warehouse;
import pl.agh.restaurant_project.repository.WarehouseRepository;
import pl.agh.restaurant_project.service.WarehouseService;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class WarehouseController {
    private final WarehouseRepository repository;
    private final WarehouseService service;

    WarehouseController(WarehouseRepository repository, WarehouseService service)
    {
        this.repository = repository;
        this.service = service;
    }

    @RequestMapping(value = "warehouse/all", method = RequestMethod.GET)
    public ModelAndView warehouses()
    {
        ModelAndView mav = new ModelAndView("warehouse/all");
        mav.addObject("warehouses", repository.findAll());
        return mav;
    }

    @RequestMapping("/warehouse/create")
    public String showNewWarehousePage(Model model) {
        Warehouse supply = new Warehouse();
        model.addAttribute("warehouse", supply);
        return "warehouse/create";
    }

    @RequestMapping(value = "/warehousesave", method = RequestMethod.POST)
    public String saveWarehouse(@ModelAttribute("warehouse") Warehouse supply, Model model, BindingResult result) {
        service.save(supply);

        model.addAttribute("warehouses", repository.findAll() );
        return "redirect:warehouse/all";
    }

    @RequestMapping("/warehouse/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/warehouse/all";
    }

    @RequestMapping(path = "/warehouseupdate/{id}", method = RequestMethod.POST)
    public String updateUser(@PathVariable("id") Optional<Long> id, @ModelAttribute("warehouse") Warehouse supply
            , Model model, BindingResult result)
    {
        if (id.isPresent()) {
            service.update(id.get(), supply);
        }

        model.addAttribute("warehouses", repository.findAll() );
        return "redirect:/warehouse/all";
    }

    @RequestMapping("/warehouse/edit/{id}")
    public String editWarehouseById(Model model, @PathVariable("id") Optional<Long> id) {
        if (id.isPresent()) {
            Warehouse supply = service.get(id.get());
            model.addAttribute("warehouse", supply);
            model.addAttribute("supplyId", id.get());
        }

        return "/warehouse/edit";
    }
    @RequestMapping("/downloadCSVWarehouse")
    public void downloadCSV(HttpServletResponse response) throws IOException {

        String csvFileName = "products.csv";

        response.setContentType("text/csv");
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        List<Warehouse> tmp= repository.findAll();
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);

        String[] header = { "nameOfProduct","amount" };

        csvWriter.writeHeader(header);

        for (Warehouse product : tmp) {
            csvWriter.write(product, header);
        }

        csvWriter.close();
    }


}
