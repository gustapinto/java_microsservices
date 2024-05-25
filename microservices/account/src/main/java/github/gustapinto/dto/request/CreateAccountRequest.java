package github.gustapinto.dto.request;

import java.util.UUID;

public record CreateAccountRequest(
    String name,
    double initialValue,
    UUID userId
) {
}
