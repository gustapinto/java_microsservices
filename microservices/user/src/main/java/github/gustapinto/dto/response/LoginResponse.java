package github.gustapinto.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginResponse(
    @JsonProperty("token")
    String token
) {
}
