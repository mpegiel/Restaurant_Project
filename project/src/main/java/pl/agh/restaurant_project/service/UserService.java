package pl.agh.restaurant_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.restaurant_project.domain.User;
import pl.agh.restaurant_project.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public User login(String Username, String PersonPassword) {
        return userRepo.findByUsernameAndPersonPassword(Username, PersonPassword);
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
            user.setPersonEmail(updatedUser.getPersonEmail());
            user.setPersonPassword(updatedUser.getPersonPassword());
            return userRepo.save(user);
        }
        return null;
    }
}
