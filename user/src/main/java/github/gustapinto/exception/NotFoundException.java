package github.gustapinto.exception;

public class NotFoundException extends Exception {
    public NotFoundException(String entity) {
        super(String.format("Entity %s not found", entity));
    }
}
