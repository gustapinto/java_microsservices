package github.gustapinto.common.exception;

public class NotBlankFieldException extends IllegalArgumentException {
    public NotBlankFieldException(String field) {
        super(String.format("Field %s must not be blank", field));
    }
}
