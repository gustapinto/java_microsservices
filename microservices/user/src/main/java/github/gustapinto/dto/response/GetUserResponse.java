package github.gustapinto.dto.response;

import java.time.Instant;
import java.util.UUID;

import org.eclipse.microprofile.jwt.JsonWebToken;

import github.gustapinto.model.User;

public record GetUserResponse(
    UUID id,
    String name,
    String email,
    Instant created_at,
    Instant updated_at
) {
    public static GetUserResponse from(User user) {
        return new GetUserResponse(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }

    public static GetUserResponse from(JsonWebToken jwt) {
        return new GetUserResponse(
            UUID.fromString(jwt.getClaim("id")),
            jwt.getClaim("name"),
            jwt.getClaim("email"),
            Instant.parse(jwt.getClaim("createdAt")),
            Instant.parse(jwt.getClaim("updatedAt"))
        );
    }
}
