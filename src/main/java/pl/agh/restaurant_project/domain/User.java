package pl.agh.restaurant_project.domain;

import javax.persistence.*;

@Entity
@Table(name="Person")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long personId;
    private String username;
    private String personPassword;
    private String personEmail;
    private String firstName;
    private String personSurname;
    private String personSalary;
    private String resetPasswordToken;

    public User () {

    }
    public User(Long PersonId, String PersonUsername, String PersonPassword, String PersonEmail, String firstName,
    String PersonSurname, String PersonSalary) {
        this.personId = PersonId;
        this.username = PersonUsername;
        this.personPassword = PersonPassword;
        this.personEmail = PersonEmail;
        this.firstName = firstName;
        this.personSurname = PersonSurname;
        this.personSalary = PersonSalary;
    }


    public Long getPersonId() {
        return personId;
    }
    public void setPersonId(Long id) {
        this.personId = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
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
    public void setResetPasswordToken(String token) {this.resetPasswordToken = token;}
    public String getFirstName() { return firstName; }
    public void setFirstName(String personName) { this.firstName = personName; }
    public String getPersonSurname() { return personSurname; }
    public void setPersonSurname(String personSurname) { this.personSurname = personSurname; }

}
