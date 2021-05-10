package pl.agh.restaurant_project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JsonIgnore
    pl.agh.restaurant_project.domain.Event from;

    @ManyToOne
    @JsonIgnore
    pl.agh.restaurant_project.domain.Event to;

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

    public pl.agh.restaurant_project.domain.Event getFrom() {
        return from;
    }

    public void setFrom(pl.agh.restaurant_project.domain.Event from) {
        this.from = from;
    }

    public pl.agh.restaurant_project.domain.Event getTo() {
        return to;
    }

    public void setTo(pl.agh.restaurant_project.domain.Event to) {
        this.to = to;
    }
}
