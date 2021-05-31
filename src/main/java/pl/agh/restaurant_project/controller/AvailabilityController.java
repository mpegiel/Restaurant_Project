package pl.agh.restaurant_project.controller;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.agh.restaurant_project.domain.Resource;
import pl.agh.restaurant_project.repository.AvailabilityEventRepository;
import pl.agh.restaurant_project.domain.AvailabilityEvent;
import pl.agh.restaurant_project.repository.ResourceRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RestController
public class AvailabilityController {


    @Autowired
    AvailabilityEventRepository er;

    @Autowired
    ResourceRepository rr;


    @RequestMapping("/availability")
    public ModelAndView index () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("availability/availability");
        return modelAndView;
    }


    @GetMapping("/api/availabilityevents")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    Iterable<AvailabilityEvent> events(@RequestParam("start") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime start, @RequestParam("end") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime end) {
        return er.findBetween(start, end);
    }

    @PostMapping("/api/availabilityevents/create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    AvailabilityEvent createEvent(@RequestBody AvailabilityEventCreateParams params) {

        Resource r = rr.findById(params.resource).get();

        AvailabilityEvent e = new AvailabilityEvent();
        e.setStart(params.start);
        e.setEnd(params.end);
        e.setText(params.text);
        e.setResource(r);

        er.save(e);

        return e;
    }

    @PostMapping("/api/availabilityevents/move")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    AvailabilityEvent moveEvent(@RequestBody AvailabilityEventMoveParams params) {

        AvailabilityEvent e = er.findById(params.id).get();
        Resource r = rr.findById(params.resource).get();

        e.setStart(params.start);
        e.setEnd(params.end);
        e.setResource(r);

        er.save(e);

        return e;
    }

    @PostMapping("/api/availabilityevents/setColor")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    AvailabilityEvent setColor(@RequestBody SetColorParams params) {

        AvailabilityEvent e = er.findById(params.id).get();
        e.setColor(params.color);
        er.save(e);

        return e;
    }

    public static class AvailabilityEventCreateParams {
        public LocalDateTime start;
        public LocalDateTime end;
        public String text;
        public Long resource;
    }

    public static class AvailabilityEventMoveParams {
        public Long id;
        public LocalDateTime start;
        public LocalDateTime end;
        public Long resource;
    }

    public static class SetColorParams {
        public Long id;
        public String color;
    }

}