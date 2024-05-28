package github.gustapinto.common.exception;

public class NotFoundException extends jakarta.ws.rs.NotFoundException {
    public NotFoundException(String entity) {
        super(String.format("Entity %s not found", entity));
    }
}
