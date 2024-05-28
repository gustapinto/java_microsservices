package github.gustapinto.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateAccountRequest(
    @JsonProperty("name")
    String name,

    @JsonProperty("current_value")
    double currentValue
) {
}
