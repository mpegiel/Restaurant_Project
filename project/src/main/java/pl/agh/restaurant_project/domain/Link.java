package org.daypilot.demo.machinescheduling.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JsonIgnore
    Event from;

    @ManyToOne
    @JsonIgnore
    Event to;

    @JsonProperty("from")
    public Long getFromId() {
        return from.getId();
    }

    @JsonProperty("to")
    public Long getToId() {
        return to.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getFrom() {
        return from;
    }

    public void setFrom(Event from) {
        this.from = from;
    }

    public Event getTo() {
        return to;
    }

    public void setTo(Event to) {
        this.to = to;
    }
}
