package github.gustapinto.dto.request;

public record UpdateAccountRequest(
    String name,
    double currentValue
) {
}
