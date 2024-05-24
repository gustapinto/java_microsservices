package github.gustapinto.model;

import java.time.Instant;
import java.util.UUID;

import github.gustapinto.common.validation.NotBlankValidator;
import github.gustapinto.common.validation.NotNullValidator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID userId;
    private String name;
    @Column(name = "initial_value")
    private double initialValue;
    @Column(name = "current_value")
    private double currentValue;
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) throws IllegalArgumentException {
        NotNullValidator.validate("user_id", userId);

        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        NotNullValidator.validate("name", name);
        NotBlankValidator.validate("name", name);

        this.name = name;
    }

    public double getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(double initialValue) {
        this.initialValue = initialValue;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) throws IllegalArgumentException {
        NotNullValidator.validate("created_at", createdAt);

        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) throws IllegalArgumentException {
        NotNullValidator.validate("updated_at", updatedAt);

        this.updatedAt = updatedAt;
    }
}

