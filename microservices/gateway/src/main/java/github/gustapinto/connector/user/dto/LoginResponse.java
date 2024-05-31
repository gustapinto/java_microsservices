package github.gustapinto.connector.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginResponse(
    @JsonProperty("token")
    String token
) {
}
