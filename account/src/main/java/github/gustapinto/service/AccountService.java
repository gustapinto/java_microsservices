package github.gustapinto.service;

import java.time.Instant;
import java.util.UUID;

import github.gustapinto.common.exception.NotFoundException;
import github.gustapinto.model.Account;
import github.gustapinto.repository.AccountRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AccountService {
    @Inject
    AccountRepository accountRepository;

    @Transactional
    public UUID create(String name, double initialValue, UUID userId) throws IllegalArgumentException {
        var account = new Account();
        account.setName(name);
        account.setInitialValue(initialValue);
        account.setCurrentValue(initialValue);
        account.setUserId(userId);
        account.setCreatedAt(Instant.now());
        account.setUpdatedAt(Instant.now());
        accountRepository.persist(account);

        return account.getId();
    }

    public Account getById(UUID id) throws NotFoundException {
        var account = accountRepository.findById(id);
        if (account == null) {
            throw new NotFoundException(Account.class.getName());
        }

        return account;
    }

    @Transactional
    public void updateById(UUID id, String name, double currentValue) throws NotFoundException {
        var account = this.getById(id);

        account.setName(name);
        account.setCurrentValue(currentValue);
        accountRepository.persist(account);
    }

    @Transactional
    public void deleteById(UUID id) throws NotFoundException {
        var found = accountRepository.deleteById(id);
        if (!found) {
            throw new NotFoundException(Account.class.getName());
        }
    }
}
