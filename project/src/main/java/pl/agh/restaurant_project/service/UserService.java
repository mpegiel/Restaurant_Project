package pl.agh.restaurant_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.restaurant_project.domain.User;
import pl.agh.restaurant_project.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User login(String PersonLogin, String PersonPassoword) {
        User user = repo.findByPersonLoginAndPersonPassoword(PersonLogin, PersonPassoword);
        return user;
    }

}
