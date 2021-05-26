package pl.agh.restaurant_project.domain;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private int quantity;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "meal_id")
    private Menu meal;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem() {};
    public OrderItem(Long id, int quantity, Menu meal, Order order) {
        super();
        this.id = id;
        this.quantity = quantity;
        this.meal = meal;
        this.order = order;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {this.quantity = quantity;}
    public Menu getMeal() {return meal;}
    public void setMeal(Menu meal) {this.meal = meal;}
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
}
