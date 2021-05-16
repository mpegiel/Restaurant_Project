package pl.agh.restaurant_project.domain;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name="Person", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User implements UserDetails {
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



    public User () {

    }

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "person_role",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> authorities;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
    @Override
    public String getPassword() { return this.personPassword; }
    @Column
    public boolean isAccountNonExpired() { return true; }
    @Column
    public boolean isAccountNonLocked() { return true; }
    @Column
    public boolean isCredentialsNonExpired() { return true; }
    @Column
    public boolean isEnabled() { return true; }

    public Long getPersonId() {
        return personId;
    }
    public void setPersonId(Long id) {
        this.personId = id;
    }
    public void setUsername(String username) {
        this.username = username;
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
