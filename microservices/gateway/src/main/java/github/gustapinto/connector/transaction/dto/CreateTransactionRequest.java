package github.gustapinto.connector.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateTransactionRequest(
    @JsonProperty("name")
    String name,

    @JsonProperty("value")
    double value
) {
}
