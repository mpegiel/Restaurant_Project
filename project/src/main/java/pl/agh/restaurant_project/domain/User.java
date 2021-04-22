package pl.agh.restaurant_project.domain;

import javax.persistence.*;

@Entity
@Table(name="Person")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long personId;
    private String personLogin;
    private String personPassword;
    private String personEmail;
    private String personName;
    private String personSurname;
    private String personSalary;

    public User () {

    }
    public User(Long PersonId, String PersonUsername, String PersonPassword, String PersonEmail, String PersonName,
    String PersonSurname, String PersonSalary) {
        this.personId = PersonId;
        this.personLogin = PersonUsername;
        this.personPassword = PersonPassword;
        this.personEmail = PersonEmail;
        this.personName = PersonName;
        this.personSurname = PersonSurname;
        this.personSalary = PersonSalary;
    }
    public Long getPersonId() {
        return personId;
    }
    public void setPersonId(Long id) {
        this.personId = id;
    }
    public String getPersonLogin() {
        return personLogin;
    }
    public void setPersonLogin(String username) {
        this.personLogin = username;
    }
    public String getPersonPassword() {
        return personPassword;
    }
    public void setPersonPassword(String password) {
        this.personPassword = password;
    }
    public String getPersonSalary() {
        return personSalary;
    }
    public void setPersonSalary(String personSalary) {
        this.personSalary = personSalary;
    }
    public String getPersonEmail() { return personEmail; }
    public void setPersonEmail(String personEmail) { this.personEmail = personEmail; }
    public String getPersonName() { return personName; }
    public void setPersonName(String personName) { this.personName = personName; }
    public String getPersonSurname() { return personSurname; }
    public void setPersonSurname(String personSurname) { this.personSurname = personSurname; }
}
