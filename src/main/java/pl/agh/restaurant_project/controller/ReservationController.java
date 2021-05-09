package pl.agh.restaurant_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.agh.restaurant_project.domain.Reservation;
import pl.agh.restaurant_project.domain.User;
import pl.agh.restaurant_project.repository.ReservationRepository;
import pl.agh.restaurant_project.service.ReservationService;

import java.util.Optional;

@Controller
public class ReservationController {

@Autowired
    private final ReservationService reservationService;
    private final ReservationRepository reservationRepo;

    ReservationController(ReservationRepository reservationRepo, ReservationService reservationService) {
        this.reservationRepo = reservationRepo;
        this.reservationService = reservationService;
    }

    @RequestMapping(value = "reservation/all", method = RequestMethod.GET)
    public ModelAndView reservations() {
        ModelAndView mav = new ModelAndView("reservation/all");
        mav.addObject("reservations", reservationRepo.findAll());
        return mav;
    }

    @RequestMapping("/reservation/create")
    public String showNewReservationPage(Model model) {
        Reservation reservation = new Reservation();
        model.addAttribute("reservation", reservation);
        return "reservation/create";
    }

    @RequestMapping(value = "/reservationsave", method = RequestMethod.POST)
    public String saveReservation(@ModelAttribute("reservation") Reservation reservation, Model model, BindingResult result) {
        reservationService.save(reservation);
        model.addAttribute("reservations", reservationRepo.findAll() );
        return "redirect:reservation/all";
    }

    @RequestMapping(path = "/reservationupdate/{id}", method = RequestMethod.POST)
    public String updateUser(@PathVariable("id") Optional<Long> id, @ModelAttribute("reservation") Reservation reservation, Model model, BindingResult result) {
        if (id.isPresent()) {
            reservationService.update(id.get(), reservation);
        }

        model.addAttribute("reservations", reservationRepo.findAll() );
        return "redirect:/reservation/all";
    }

    @RequestMapping("/reservation/edit/{id}")
    public String editReservationById(Model model, @PathVariable("id") Optional<Long> id) {
        if (id.isPresent()) {
            Reservation reservation = reservationService.get(id.get());
            model.addAttribute("reservation", reservation);
            model.addAttribute("reservationID", id.get());
        }

        return "/reservation/edit";
    }

    @RequestMapping("/reservation/delete/{id}")
    public String deleteReservation(@PathVariable(name = "id") int id) {
        reservationService.delete(id);
        return "redirect:/reservation/all";
    }

}
