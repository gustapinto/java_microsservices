package github.gustapinto.service;

import java.time.Instant;
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

    public Account getById(@NotNull UUID id) throws NotFoundException, ConstraintViolationException {
        Account account = Account.findById(id);
        if (account == null) {
            throw new NotFoundException(Account.class.getName());
        }

        return account;
    }

    @Transactional
    public void updateById(@NotNull UUID id, @NotBlank String name, double currentValue) throws NotFoundException, ConstraintViolationException {
        var account = this.getById(id);

        account.name = name;
        account.currentValue = currentValue;
        account.persist();
    }

    @Transactional
    public void deleteById(@NotNull UUID id) throws NotFoundException, ConstraintViolationException {
        var found = Account.deleteById(id);
        if (!found) {
            throw new NotFoundException(Account.class.getName());
        }
    }
}
