package github.gustapinto.service;

import github.gustapinto.common.exception.NotFoundException;
import github.gustapinto.common.exception.UnauthorizedException;
import github.gustapinto.enums.RoleEnum;
import github.gustapinto.model.User;
import github.gustapinto.repository.UserRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AuthService {
    @Inject
    UserRepository userRepository;

    public String login(String email, String password) throws UnauthorizedException {
       var user = userRepository.find("email", email).firstResult();
        if (user == null) {
            throw new NotFoundException(User.class.getName());
        }

        var isPasswordValid = BcryptUtil.matches(password, user.getPassword());
        if (!isPasswordValid) {
            throw new UnauthorizedException(email);
        }

        return Jwt.issuer("http://localhost:8080/v1/auth/login")
            .upn(user.getEmail())
            .groups(RoleEnum.USER)
            .claim("id", user.getId().toString())
            .claim("name", user.getName())
            .claim("email", user.getEmail())
            // OBS: uses camelCase for claims to avoid reserved keys collision
            .claim("createdAt", user.getCreatedAt().toString())
            .claim("updatedAt", user.getUpdatedAt().toString())
            .sign();
    }
}
