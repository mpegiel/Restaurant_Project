package pl.agh.restaurant_project.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.agh.restaurant_project.domain.User;

import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPersonPassword(String Username, String PersonPassword);
    @Query("SELECT c FROM User c WHERE c.personEmail = ?1")
    public User findByEmail(String email);
    @Query("SELECT c FROM User c WHERE c.resetPasswordToken = ?1")
    public User findByResetPasswordToken(String token);
}

