package pl.agh.restaurant_project.domain;

import org.hibernate.annotations.ManyToAny;
import org.springframework.security.core.GrantedAuthority;
import pl.agh.restaurant_project.domain.User;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    private static final String PREFIX = "ROLE_";

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String authority;

    @ManyToMany(mappedBy = "authorities")
    private final Set<User> users = new HashSet<>(0);

    public Role() {

    }

    @Override
    public String getAuthority() {
        return null;
    }


    public enum RoleEnum {
        ADMIN,
        USER
    }

    public Role(RoleEnum authority) {
        this.authority = PREFIX + authority.toString();
    }

    @Override
    public String toString() {
        return authority;
    }

}
