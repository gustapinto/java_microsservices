package github.gustapinto.dto.request;

public record CreateUserRequest(
    String name,
    String email,
    String password
) {
}
