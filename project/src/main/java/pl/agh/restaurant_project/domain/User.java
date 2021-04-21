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

    public User () {

    }
    public User(Long PersonId, String PersonUsername, String PersonPassword) {
        this.personId = PersonId;
        this.personLogin = PersonUsername;
        this.personPassword = PersonPassword;
    }
    public Long getPersonId() {
        return personId;
    }
    public void setPersonId(Long id) {
        this.personId = id;
    }
    public String getPersonLogin() {return personLogin;}
    public void setPersonLogin(String username) {this.personLogin = username; }
    public String getPersonPassword() {
        return personPassword;
    }
    public void setPersonPassword(String password) {
        this.personPassword = password;
    }
}
