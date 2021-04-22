package pl.agh.restaurant_project.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.agh.restaurant_project.domain.User;

import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {
    User findByPersonLoginAndPersonPassword(String PersonLogin, String PersonPassword);
}
