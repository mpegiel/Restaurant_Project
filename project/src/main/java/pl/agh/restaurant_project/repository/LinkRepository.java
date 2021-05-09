package org.daypilot.demo.machinescheduling.repository;

import org.daypilot.demo.machinescheduling.domain.Event;
import org.daypilot.demo.machinescheduling.domain.Link;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.LocalDateTime;
import java.util.List;

public interface LinkRepository extends CrudRepository<Link, Long> {
    List<Link> findByTo(Event e);
    List<Link> findByFrom(Event e);
}