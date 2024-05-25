package github.gustapinto.common.dto.response;

public record ErrorResponse(
    String type,
    String message
) {
    public static ErrorResponse from(Throwable e) {
        return new ErrorResponse(
            e.getClass().getName(),
            e.getMessage()
        );
    }
}
