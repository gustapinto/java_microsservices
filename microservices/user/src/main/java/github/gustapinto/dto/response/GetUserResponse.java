package github.gustapinto.dto.response;

import java.time.Instant;
import java.util.UUID;

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
}
