package pl.agh.restaurant_project.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Entity
@Table(name="Person", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long personId;
    @Column(nullable = false, unique = true, length = 20)
    private String username;
    @Column(nullable = false)
    private String personPassword;
    @Column(nullable = false)
    private String personEmail;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String personSurname;
    @Column
    private String personSalary;
    @Column
    private String resetPasswordToken;

    private String roles = "";


    public User() {

    }

    public User(Long PersonId, String PersonUsername, String PersonPassword, String PersonEmail, String firstName,
    String PersonSurname, String PersonSalary, String roles) {
        this.personId = PersonId;
        this.username = PersonUsername;
        this.personPassword = PersonPassword;
        this.personEmail = PersonEmail;
        this.firstName = firstName;
        this.personSurname = PersonSurname;
        this.personSalary = PersonSalary;
        this.roles = roles;
    }

    public Long getPersonId() {
        return personId;
    }
    public void setPersonId(Long id) {
        this.personId = id;
    }
    public String getUsername() { return username; }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPersonPassword() { return this.personPassword; }
    public void setPersonPassword(String password) { this.personPassword = password; }
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
    public String getRoles() { return roles; }
    public List<String> getRoleList() {
        if(this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

}
