package pl.agh.restaurant_project.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;


@Entity
@Table(name="Menu")
public class Menu {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String nameOfMeal;
    private double price;

    public Menu() {}

    public Menu(String nameOfMeal, double price) {
        this.nameOfMeal = nameOfMeal;
        this.price = price;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfMeal() {
        return nameOfMeal;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setNameOfMeal(String nameOfMeal) {
        this.nameOfMeal = nameOfMeal;
    }
}
