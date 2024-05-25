package github.gustapinto.dto.request;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateTransactionRequest(
    @JsonProperty("name")
    String name,

    @JsonProperty("value")
    double value,

    @JsonProperty("user_id")
    UUID userId,

    @JsonProperty("account_id")
    UUID accountId
) {
}
