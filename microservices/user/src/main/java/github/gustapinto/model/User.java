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
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) throws IllegalArgumentException {
        NotNullValidator.validate("id", name);

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        NotNullValidator.validate("name", name);
        NotBlankValidator.validate("name", name);

        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws IllegalArgumentException {
        NotNullValidator.validate("email", name);
        NotBlankValidator.validate("email", name);

        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws IllegalArgumentException {
        NotNullValidator.validate("password", name);
        NotBlankValidator.validate("password", name);

        this.password = password;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) throws IllegalArgumentException {
        NotNullValidator.validate("createdAt", name);

        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) throws IllegalArgumentException {
        NotNullValidator.validate("updatedAt", name);

        this.updatedAt = updatedAt;
    }
}
