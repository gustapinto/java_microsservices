package github.gustapinto.connector.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateUserRequest(
    @JsonProperty("name")
    String name
) {
}
