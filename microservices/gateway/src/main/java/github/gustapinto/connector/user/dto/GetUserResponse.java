package github.gustapinto.connector.user.dto;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GetUserResponse(
    @JsonProperty("id")
    UUID id,

    @JsonProperty("name")
    String name,

    @JsonProperty("email")
    String email,

    @JsonProperty("created_at")
    Instant createdAt,

    @JsonProperty("updated_at")
    Instant updatedAt
) {
}
