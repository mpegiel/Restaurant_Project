package pl.agh.restaurant_project.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Orders")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String code;

    @OneToMany
    List<OrderItem> orderItems;

    public Order() {}

    public Order(Long id, String code) {
        this.id = id;
        this.code = code;
        this.orderItems = new ArrayList<OrderItem>();
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getCode() {return code;}
    public void setCode(String code) {this.code = code;}

    public List<OrderItem> getOrderItems() {return orderItems;}
    public void setOrderItems(List<OrderItem> orderItems) {this.orderItems = orderItems;}
}
