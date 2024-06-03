package github.gustapinto.connector.transaction.dto;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GetTransactionResponse(
    @JsonProperty("id")
    UUID id,

    @JsonProperty("user_id")
    UUID userId,

    @JsonProperty("account_id")
    UUID accountId,

    @JsonProperty("name")
    String name,

    @JsonProperty("value")
    double value,

    @JsonProperty("created_at")
    Instant createdAt
) {
}
