package github.gustapinto.service;

import java.time.Instant;
import java.util.UUID;

import github.gustapinto.exception.NotFoundException;
import github.gustapinto.model.User;
import github.gustapinto.repository.UserRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    public UUID create(String name, String email, String password) throws IllegalArgumentException {
        var user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(BcryptUtil.bcryptHash(password));
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        userRepository.persist(user);

        return user.getId();
    }

    public User getById(UUID id) throws NotFoundException {
        var user = userRepository.findById(id);
        if (user == null) {
            throw new NotFoundException(User.class.getName());
        }

        return user;
    }

    public void updateById(UUID id, String name) throws NotFoundException {
        var user = this.getById(id);

        user.setName(name);
        userRepository.persist(user);
    }

    public void deleteById(UUID id) throws NotFoundException {
        var found = userRepository.deleteById(id);
        if (!found) {
            throw new NotFoundException(User.class.getName());
        }
    }

    public boolean isPasswordValidForEmail(String email, String password) throws NotFoundException {
        var user = userRepository.find("email", email).firstResult();
        if (user == null) {
            throw new NotFoundException(User.class.getName());
        }

        return BcryptUtil.matches(password, user.getPassword());
    }
}
