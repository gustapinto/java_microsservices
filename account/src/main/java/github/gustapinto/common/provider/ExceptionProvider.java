package github.gustapinto.common.provider;

import github.gustapinto.common.dto.response.ErrorResponse;
import github.gustapinto.common.exception.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionProvider implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable e) {
        Status code = switch (e) {
            case NotFoundException nfe -> Status.NOT_FOUND;
            case IllegalArgumentException iae -> Status.BAD_REQUEST;
            default -> Status.INTERNAL_SERVER_ERROR;
        };

        return Response.status(code).entity(ErrorResponse.from(e)).build();
    }
}
