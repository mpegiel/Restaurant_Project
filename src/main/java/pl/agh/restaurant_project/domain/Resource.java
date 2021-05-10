package pl.agh.restaurant_project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    String name;

    @ManyToOne
    @JsonIgnore
    pl.agh.restaurant_project.domain.ResourceGroup parent;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public pl.agh.restaurant_project.domain.ResourceGroup getParent() {
        return parent;
    }

    public void setParent(pl.agh.restaurant_project.domain.ResourceGroup parent) {
        this.parent = parent;
    }
}
