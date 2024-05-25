package github.gustapinto.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import github.gustapinto.common.exception.NotFoundException;
import github.gustapinto.model.Transaction;
import io.smallrye.common.constraint.NotNull;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotBlank;

@ApplicationScoped
public class TransactionService {
    @Transactional
    public UUID create(@NotBlank String name, double value, @NotNull UUID userId, @NotNull UUID accountId) throws ConstraintViolationException {
        var transaction = new Transaction();
        transaction.name = name;
        transaction.userId = userId;
        transaction.accountId = accountId;
        transaction.value = value;
        transaction.createdAt = Instant.now();
        transaction.persist();

        return transaction.id;
    }

    public List<Transaction> getAll(@NotNull UUID userId, @NotNull UUID accountId) throws ConstraintViolationException {
        List<Transaction> transactions = Transaction.list(
                "userId = ?1 and accountId = ?2",
                userId,
                accountId
            );
        if (transactions == null) {
            return List.of();
        }

        return transactions;
    }

    public Transaction getById(@NotNull UUID id, @NotNull UUID userId, @NotNull UUID accountId) throws NotFoundException, ConstraintViolationException {
        Transaction transaction = Transaction.find(
                "id = ?1 and userId = ?2 and accountId = ?3",
                id,
                userId,
                accountId
            )
            .firstResult();
        if (transaction == null) {
            throw new NotFoundException(Transaction.class.getName());
        }

        return transaction;
    }

    @Transactional
    public void deleteById(@NotNull UUID id, @NotNull UUID userId, @NotNull UUID accountId) throws NotFoundException, ConstraintViolationException {
        getById(id, userId, accountId).delete();
    }
}
