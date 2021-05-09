package pl.agh.restaurant_project.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;


@Entity
@Table(name="Reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private int tableNumber;
    private String date;
    private String personData;
    private String telephoneNumber;

    public Reservation() {}

    public Reservation(int tableNumber, String date, String personData, String telephoneNumber) {
        this.tableNumber = tableNumber;
        this.date = date;
        this.personData = personData;
        this.telephoneNumber = telephoneNumber;
    }
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }
    public int getTableNumber() {
        return tableNumber;
    }
    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getPersonData() {
        return personData;
    }
    public void setPersonData(String personData) {
        this.personData = personData;
    }
    public String getTelephoneNumber() {
        return telephoneNumber;
    }
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

}
