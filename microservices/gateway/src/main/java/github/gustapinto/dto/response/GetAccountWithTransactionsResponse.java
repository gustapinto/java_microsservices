package github.gustapinto.dto.response;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import github.gustapinto.connector.transaction.dto.GetTransactionResponse;

public record GetAccountWithTransactionsResponse(
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
    Instant updatedAt,

    @JsonProperty("transactions")
    List<GetTransactionResponse> transactions
) {
}
