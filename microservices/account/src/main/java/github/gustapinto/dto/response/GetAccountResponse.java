package github.gustapinto.dto.response;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import github.gustapinto.model.Account;

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
    public static GetAccountResponse from(Account account) {
        return new GetAccountResponse(
            account.id,
            account.userId,
            account.name,
            account.initialValue,
            account.currentValue,
            account.createdAt,
            account.updatedAt
        );
    }

    public static List<GetAccountResponse> from(List<Account> accounts) {
        return accounts.stream()
            .map(a -> GetAccountResponse.from(a))
            .toList();
    }
}
