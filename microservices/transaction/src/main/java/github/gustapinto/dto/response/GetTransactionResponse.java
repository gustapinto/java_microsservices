package github.gustapinto.dto.response;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import github.gustapinto.model.Transaction;

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
    public static GetTransactionResponse from(Transaction transaction) {
        return new GetTransactionResponse(
            transaction.id,
            transaction.userId,
            transaction.accountId,
            transaction.name,
            transaction.value,
            transaction.createdAt
        );
    }

    public static List<GetTransactionResponse> from(List<Transaction> transactions) {
        return transactions.stream()
            .map(t -> GetTransactionResponse.from(t))
            .toList();
    }
}
