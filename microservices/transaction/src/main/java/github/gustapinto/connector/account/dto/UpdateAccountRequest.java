package github.gustapinto.connector.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateAccountRequest(
    @JsonProperty("name")
    String name,

    @JsonProperty("current_value")
    double currentValue
) {
}

