package github.gustapinto.common.exception;

public class NotNullFieldException extends IllegalArgumentException {
    public NotNullFieldException(String field) {
        super(String.format("Field %s must not be null", field));
    }
}
