package github.gustapinto.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import github.gustapinto.dto.request.LoginRequest;
import github.gustapinto.dto.response.GetUserResponse;
import github.gustapinto.dto.response.LoginResponse;
import github.gustapinto.enums.RoleEnum;
import github.gustapinto.service.AuthService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("")
@RequestScoped
public class AuthResource {
    @Inject
    AuthService authService;

    @Inject
    JsonWebToken jwt;

    @POST
    @Path("/v1/auth/login")
    public Response login(LoginRequest req) {
        var token = authService.login(req.email(), req.password());
        var res = new LoginResponse(token);

        return Response.ok(res).build();
    }

    @GET
    @RolesAllowed(value = {RoleEnum.USER})
    @Path("/v1/auth/decode")
    public Response decode() {
        var res = GetUserResponse.from(jwt);

        return Response.ok(res).build();
    }
}
