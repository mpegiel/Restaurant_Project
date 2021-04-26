package pl.agh.restaurant_project.service;

import org.apache.commons.codec.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.agh.restaurant_project.domain.User;
import pl.agh.restaurant_project.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public User login(String Username, String PersonPassword) {
        return userRepo.findByUsernameAndPersonPassword(Username, PersonPassword);
    }

    public void updateResetPasswordToken(String token, String email) throws UsernameNotFoundException{
        User user = userRepo.findByEmail(email);
        if (user!=null){
            user.setResetPasswordToken(token);
            userRepo.save(user);
        }
        else {
            throw new UsernameNotFoundException("Could not find any customer with the email " + email);
        }
    }

    public User getByResetPasswordToken(String token){
        return userRepo.findByResetPasswordToken(token);
    }

    public void updatePassword(User user, String newPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPersonPassword(encodedPassword);

        user.setResetPasswordToken(null);
        userRepo.save(user);
    }

    public List<User> listAll() {
        return userRepo.findAll();
    }

    public void save(User user) {
        userRepo.save(user);
    }

    public User get(Long id) {
        return userRepo.findById(id).get();
    }

    public void delete(long id) {
        userRepo.deleteById(id);
    }

    public User update(Long ID, User updatedUser) {
        Optional<User> optUser = userRepo.findById(ID);

        if (optUser.isPresent()) {
            User user = optUser.get();
            user.setFirstName(updatedUser.getFirstName());
            user.setPersonSurname(updatedUser.getPersonSurname());
            user.setPersonEmail(updatedUser.getPersonEmail());
            user.setUsername(updatedUser.getUsername());
            user.setPersonPassword(updatedUser.getPersonPassword());
            user.setPersonSalary(updatedUser.getPersonSalary());
            return userRepo.save(user);
        }
        return null;
    }
}
