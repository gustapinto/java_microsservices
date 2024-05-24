package github.gustapinto.repository;

import java.util.UUID;

import github.gustapinto.model.Account;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccountRepository implements PanacheRepositoryBase<Account, UUID> {
}
