package pl.agh.restaurant_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.agh.restaurant_project.domain.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByAuthority(String authority);
    Optional<Role> findById(Long id);

}
