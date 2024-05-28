package github.gustapinto.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateAccountRequest(
    @JsonProperty("name")
    String name,

    @JsonProperty("initial_value")
    double initialValue
) {
}
