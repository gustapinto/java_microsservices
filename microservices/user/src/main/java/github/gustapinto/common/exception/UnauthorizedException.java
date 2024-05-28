package github.gustapinto.common.exception;

public class UnauthorizedException extends jakarta.ws.rs.NotAuthorizedException {
    public UnauthorizedException(String email) {
        super(String.format("User %s not authorized", email));
    }
}
