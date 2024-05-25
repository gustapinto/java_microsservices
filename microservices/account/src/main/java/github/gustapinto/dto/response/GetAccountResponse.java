package github.gustapinto.dto.response;

import java.time.Instant;
import java.util.UUID;

import github.gustapinto.model.Account;

public record GetAccountResponse(
    UUID id,
    UUID userId,
    String name,
    double initialValue,
    double currentValue,
    Instant createdAt,
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
}
