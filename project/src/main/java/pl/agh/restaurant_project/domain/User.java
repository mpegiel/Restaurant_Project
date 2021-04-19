package pl.agh.restaurant_project.domain;

import javax.persistence.*;

@Entity
@Table(name="Person")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long personId;
    private String personLogin;
    private String personPassoword;

    public User () {

    }
    public User(Long PersonId, String PersonUsername, String PersonPassoword) {
        this.personId = PersonId;
        this.personLogin = PersonUsername;
        this.personPassoword = PersonPassoword;
    }
    public Long getPersonId() {
        return personId;
    }
    public void setPersonId(Long id) {
        this.personId = id;
    }
    public String getPersonLogin() {return personLogin;}
    public void setPersonLogin(String username) {this.personLogin = username; }
    public String getPersonPassoword() {
        return personPassoword;
    }
    public void setPersonPassoword(String password) {
        this.personPassoword = password;
    }
}
