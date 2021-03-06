package pl.agh.restaurant_project.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;


@Entity
@Table(name="Warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameOfProduct;
    private int amount;
    private Boolean toOrder;

    public Warehouse() {
    }

    public Warehouse(String nameOfProduct, int amount, boolean toOrder) {
        this.nameOfProduct = nameOfProduct;
        this.amount = amount;
        this.toOrder = toOrder;
    }

    public Long getId() {
        return id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public Boolean getToOrder() {
        return toOrder;
    }

    public void setToOrder(Boolean toOrder) {
        this.toOrder = toOrder;
    }
}