package pl.agh.restaurant_project.service;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.restaurant_project.domain.Role;
import pl.agh.restaurant_project.repository.RoleRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepo;

    public List findAll() {
        return roleRepo.findAll();
    }

    private Role findByAuthority(@NotNull String authority) {
        return roleRepo.findByAuthority(authority);
    }

    public Role findByAuthority(@NotNull Role.RoleEnum authority) {
        return roleRepo.findByAuthority(authority.toString());
    }

    public Optional<Role> findOne(@NotNull Long id) {
        return roleRepo.findById(id);
    }

    public Role save(@NotNull Role role) {
        return roleRepo.save(role);
    }

    public Role createRoleIfNotFound(Role.RoleEnum roleEnum) {
        Role role = this.findByAuthority(roleEnum.toString());
        if (role == null) {
            role = new Role(roleEnum);
            this.save(role);
        }
        return role;
    }
}