package pl.agh.restaurant_project.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.agh.restaurant_project.domain.User;

import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT c FROM User c WHERE c.username = ?1")
    User findByUsername(String Username);
    @Query("SELECT c FROM User c WHERE c.personId = ?1")
    Optional<User> findById(Long id);
    @Query("SELECT c FROM User c WHERE c.personEmail = ?1")
    public User findByEmail(String email);
    @Query("SELECT c FROM User c WHERE c.resetPasswordToken = ?1")
    public User findByResetPasswordToken(String token);
}

