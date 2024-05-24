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
            account.getId(),
            account.getUserId(),
            account.getName(),
            account.getInitialValue(),
            account.getCurrentValue(),
            account.getCreatedAt(),
            account.getUpdatedAt()
        );
    }
}
