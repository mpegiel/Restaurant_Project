package org.daypilot.demo.machinescheduling.domain;

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
    ResourceGroup parent;

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

    public ResourceGroup getParent() {
        return parent;
    }

    public void setParent(ResourceGroup parent) {
        this.parent = parent;
    }
}
