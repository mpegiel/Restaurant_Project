package pl.agh.restaurant_project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Event")
public class Event {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String text;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @ManyToOne
    @JsonIgnore
    private User user;
    private String color;



    public Event(Long id, String text, LocalDateTime startTime, LocalDateTime endTime, User user, String color) {
        this.id = id;
        this.text = text;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
        this.color = color;
    }

    public Event() {

    }

    @JsonProperty("user")
    public Long getUserId(){
        return user.getPersonId();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getColor() {
        return color;
    }

}
