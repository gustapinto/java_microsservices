package github.gustapinto.service;

import java.util.UUID;

import github.gustapinto.common.exception.NotFoundException;
import github.gustapinto.common.exception.UnauthorizedException;
import github.gustapinto.model.User;
import github.gustapinto.repository.UserRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;

@ApplicationScoped
public class AuthService {
    @Inject
    UserRepository userRepository;

    private String generateJwt(UUID userId, String userName, String userEmail) {
        var json = Json.createObjectBuilder()
            .add("id", userId.toString())
            .add("name", userName)
            .add("email", userEmail)
            .build();
        var claims = Jwt.claims(json);

        return claims.sign();
    }

    public String login(String email, String password) throws UnauthorizedException {
       var user = userRepository.find("email", email).firstResult();
        if (user == null) {
            throw new NotFoundException(User.class.getName());
        }

        var isPasswordValid = BcryptUtil.matches(password, user.getPassword());
        if (!isPasswordValid) {
            throw new UnauthorizedException(email);
        }

        var token = generateJwt(user.getId(), user.getName(), user.getEmail());

        return token;
    }
}
