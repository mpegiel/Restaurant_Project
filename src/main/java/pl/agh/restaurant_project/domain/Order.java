package pl.agh.restaurant_project.domain;

import javax.persistence.*;

@Entity
@Table(name="Orders")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String code;

    public Order() {}

    public Order(Long id, String code) {
        this.id = id;
        this.code = code;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getCode() {return code;}
    public void setCode(String code) {this.code = code;}
}
