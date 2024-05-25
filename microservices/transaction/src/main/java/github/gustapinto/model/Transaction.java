package github.gustapinto.model;

import java.time.Instant;
import java.util.UUID;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "transactions")
public class Transaction extends PanacheEntityBase {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @Column(name = "user_id")
    public UUID userId;

    @Column(name = "account_id")
    public UUID accountId;

    @Column(name = "name")
    public String name;

    @Column(name = "value")
    public double value;

    @Column(name = "created_at")
    public Instant createdAt;
}
