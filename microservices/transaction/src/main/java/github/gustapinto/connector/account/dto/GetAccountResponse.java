package github.gustapinto.connector.account.dto;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GetAccountResponse(
    @JsonProperty("id")
    UUID id,

    @JsonProperty("user_id")
    UUID userId,

    @JsonProperty("name")
    String name,

    @JsonProperty("initial_value")
    double initialValue,

    @JsonProperty("current_value")
    double currentValue,

    @JsonProperty("created_at")
    Instant createdAt,

    @JsonProperty("updated_at")
    Instant updatedAt
) {
}
