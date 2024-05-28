package github.gustapinto.resource;

import github.gustapinto.dto.request.LoginRequest;
import github.gustapinto.dto.response.LoginResponse;
import github.gustapinto.service.AuthService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("")
public class AuthResource {
    @Inject
    AuthService authService;

    @POST
    @Path("/v1/login")
    public Response login(LoginRequest req) {
        var token = authService.login(req.email(), req.password());
        var res = new LoginResponse(token);

        return Response.ok(res).build();
    }
}
