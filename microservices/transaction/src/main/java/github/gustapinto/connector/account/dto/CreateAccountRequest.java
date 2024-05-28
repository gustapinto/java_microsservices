package github.gustapinto.connector.account.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateAccountRequest(
    @JsonProperty("name")
    String name,

    @JsonProperty("initial_value")
    double initialValue
) {
}
