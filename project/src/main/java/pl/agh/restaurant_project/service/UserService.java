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

    public User login(String PersonLogin, String PersonPassword) {
        return userRepo.findByPersonLoginAndPersonPassword(PersonLogin, PersonPassword);
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

    public User update(User user)
    {
        Optional<User> usr = userRepo.findById(user.getPersonId());
        User newUser = usr.get();
        newUser.setPersonEmail(user.getPersonEmail());
        newUser.setPersonName(user.getPersonName());
        newUser.setPersonSurname(user.getPersonSurname());
        newUser.setPersonSalary(user.getPersonSalary());
        return userRepo.save(newUser);
    }
}
