package pl.agh.restaurant_project.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.web.servlet.ModelAndView;
import pl.agh.restaurant_project.domain.*;
import pl.agh.restaurant_project.repository.EventRepository;
import pl.agh.restaurant_project.repository.LinkRepository;
import pl.agh.restaurant_project.repository.ResourceGroupRepository;
import pl.agh.restaurant_project.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SchedulerController {

    @Autowired
    EventRepository er;

    @Autowired
    ResourceRepository rr;

    @Autowired
    ResourceGroupRepository gr;

    @Autowired
    LinkRepository lr;

    @RequestMapping("/scheduler")
    public ModelAndView index () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("scheduler/scheduler_month");
        return modelAndView;
    }

    @RequestMapping("/scheduler2")
    public ModelAndView index2 () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("scheduler/scheduler_week");
        return modelAndView;
    }

    @RequestMapping("/api/resources")
    List<ResourceGroup> resources() {
        List<ResourceGroup> groups = gr.findAllByOrderByOrdinal();

        groups.forEach(resourceGroup -> {
            List<Resource> children = rr.findByParent(resourceGroup);
            resourceGroup.setChildren(children);
        });

        return groups;
    }

    @GetMapping("/api/events")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    Iterable<Event> events(@RequestParam("start") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime start, @RequestParam("end") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime end) {
        return er.findBetween(start, end);
    }

    @GetMapping("/api/links")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    Iterable<Link> links(@RequestParam("start") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime start, @RequestParam("end") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime end) {
        return lr.findAll();
    }

    @PostMapping("/api/events/create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    UpdateResponse createEvent(@RequestBody EventCreateParams params) {

        List<Event> update = new ArrayList<>();

        Resource r = rr.findById(params.resource).orElse(null);

        Event e = new Event();
        e.setStart(params.start);
        e.setEnd(params.end);
        e.setText(params.text);
        e.setResource(r);
        er.save(e);

        e.setJoin(e.getId());
        er.save(e);

        update.add(e);

        if (params.link != null) {
            Event from = er.findById(params.link.from).orElse(null);
            from.setHasNext(true);
            er.save(from);

            update.add(from);

            Link link = new Link();
            link.setFrom(from);
            link.setTo(e);
            lr.save(link);

            e.setText(from.getText());
            e.setJoin(from.getJoin());
            e.setColor(from.getColor());
            er.save(e);
        }

        return new UpdateResponse(){{
            events = update;
        }};
    }

    @PostMapping("/api/events/move")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event moveEvent(@RequestBody EventMoveParams params) {

        Event e = er.findById(params.id).orElse(null);
        Resource r = rr.findById(params.resource).orElse(null);

        e.setStart(params.start);
        e.setEnd(params.end);
        e.setResource(r);
        er.save(e);

        return e;
    }

    @PostMapping("/api/events/delete")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    EventDeleteResponse deleteEvent(@RequestBody EventDeleteParams params) {

        List<Long> deletedIds = new ArrayList<>();
        List<Event> updatedEvents = new ArrayList<>();

        Event e = er.findById(params.id).orElse(null);

        List<Link> previous = lr.findByTo(e);
        previous.forEach(link -> {
            link.getFrom().setHasNext(false);
            er.save(link.getFrom());

            updatedEvents.add(link.getFrom());
        });

        deleteEventWithLinks(e, deletedIds);


        return new EventDeleteResponse() {{
            updated = updatedEvents;
            deleted = deletedIds;
        }};
    }

    private void deleteEventWithLinks(Event e, List<Long> deleted) {
        List<Link> toLinks = lr.findByTo(e);
        lr.deleteAll(toLinks);

        List<Link> fromLinks = lr.findByFrom(e);
        fromLinks.forEach(link -> {
            deleteEventWithLinks(link.getTo(), deleted);
        });

        er.delete(e);
        deleted.add(e.getId());

    }


    @PostMapping("/api/events/setColor")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    UpdateResponse setColor(@RequestBody SetColorParams params) {

        List<Event> list = er.findByJoin(params.join);
        list.forEach(e -> {
            e.setColor(params.color);
            er.save(e);
        });

        return new UpdateResponse() {{
            events = list;
        }};
    }

    @PostMapping("/api/events/setText")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    UpdateResponse setText(@RequestBody SetTextParams params) {

        List<Event> list = er.findByJoin(params.join);
        list.forEach(e -> {
            e.setText(params.text);
            er.save(e);
        });

        return new UpdateResponse() {{
            events = list;
        }};
    }

    public static class EventCreateParams {
        public LocalDateTime start;
        public LocalDateTime end;
        public String text;
        public Long resource;
        public LinkCreateParams link;
    }

    public static class LinkCreateParams {
        public Long from;
    }

    public static class EventMoveParams {
        public Long id;
        public LocalDateTime start;
        public LocalDateTime end;
        public Long resource;
    }

    public static class SetColorParams {
        public Long join;
        public String color;
    }

    public static class SetTextParams {
        public Long join;
        public String text;
    }

    public static class EventDeleteParams {
        public Long id;
    }

    public static class EventDeleteResponse {
        public List<Long> deleted;
        public List<Event> updated;
    }

    public static class UpdateResponse {
        public List<Event> events;
    }


}