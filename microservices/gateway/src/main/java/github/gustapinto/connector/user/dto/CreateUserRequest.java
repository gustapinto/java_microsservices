package github.gustapinto.connector.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateUserRequest(
    @JsonProperty("name")
    String name,

    @JsonProperty("email")
    String email,

    @JsonProperty("password")
    String password
) {
}
