package pl.agh.restaurant_project.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.agh.restaurant_project.domain.Event;
import pl.agh.restaurant_project.domain.User;
import pl.agh.restaurant_project.repository.EventRepository;
import pl.agh.restaurant_project.repository.UserRepository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Controller
public class SchedulerController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    EventRepository eventRepository;


    @GetMapping("/scheduler")
    public String viewSchedulerPage(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "scheduler";
    }

    @RequestMapping("/api/users")
    Iterable<User> users() {
        return userRepository.findAll();
    }

    @RequestMapping("/api/events")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    Iterable<Event> events(@RequestParam("startTime") @DateTimeFormat(iso= ISO.DATE_TIME)LocalDateTime startTime,
                           @RequestParam("endTime") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime endTime) {
        return eventRepository.findBetween(startTime, endTime);
    }

    @PostMapping("/api/events/create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event createEvent (@RequestBody EventCreateParams params) {
        User user = userRepository.findById(params.user).get();
        Event e = new Event();

        e.setStartTime(params.startTime);
        e.setEndTime(params.endTime);
        e.setText(params.text);
        e.setUser(user);

        eventRepository.save(e);

        return e;
    }

    public static class EventCreateParams {
        public LocalDateTime startTime;
        public LocalDateTime endTime;
        public String text;
        public Long user;
    }



}
