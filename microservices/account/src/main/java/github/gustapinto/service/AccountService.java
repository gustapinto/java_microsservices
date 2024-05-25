package github.gustapinto.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import github.gustapinto.common.exception.NotFoundException;
import github.gustapinto.model.Account;
import io.smallrye.common.constraint.NotNull;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotBlank;

@ApplicationScoped
public class AccountService {
    @Transactional
    public UUID create(@NotBlank String name, double initialValue, @NotNull UUID userId) throws ConstraintViolationException {
        var account = new Account();
        account.name = name;
        account.initialValue = initialValue;
        account.currentValue = initialValue;
        account.userId = userId;
        account.createdAt = Instant.now();
        account.updatedAt = Instant.now();
        account.persist();

        return account.id;
    }

    public List<Account> getAll(@NotNull UUID userId) throws ConstraintViolationException {
        List<Account> accounts = Account.list("userId", userId);
        if (accounts == null) {
            return List.of();
        }

        return accounts;
    }

    public Account getById(@NotNull UUID id, @NotNull UUID userId) throws NotFoundException, ConstraintViolationException {
        Account account = Account.find(
                "id = ?1 and userId = ?2",
                id,
                userId
            )
            .firstResult();
        if (account == null) {
            throw new NotFoundException(Account.class.getName());
        }

        return account;
    }

    @Transactional
    public void updateById(@NotNull UUID id, @NotNull UUID userId, @NotBlank String name, double currentValue) throws NotFoundException, ConstraintViolationException {
        var account = this.getById(id, userId);

        account.name = name;
        account.currentValue = currentValue;
        account.persist();
    }

    @Transactional
    public void deleteById(@NotNull UUID id, @NotNull UUID userId) throws NotFoundException, ConstraintViolationException {
        getById(id, userId).delete();
    }
}
