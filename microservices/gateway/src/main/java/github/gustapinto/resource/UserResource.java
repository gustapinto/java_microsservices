package github.gustapinto.resource;

import java.util.UUID;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import github.gustapinto.common.dto.response.CreatedResponse;
import github.gustapinto.connector.user.UserConnector;
import github.gustapinto.connector.user.dto.CreateUserRequest;
import github.gustapinto.connector.user.dto.LoginRequest;
import github.gustapinto.connector.user.dto.UpdateUserRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("")
public class UserResource {
    @RestClient
    UserConnector userConnector;

    @Inject
    @Claim
    String userId;

    @POST
    @Path("/v1/auth/login")
    public Response login(LoginRequest request) {
        var res = userConnector.login(request);

        return Response.ok(res).build();
    }

    @POST
    @Path("/v1/users")
    public Response create(CreateUserRequest request) {
        var createdResponse = userConnector.create(request);
        var res = new CreatedResponse(createdResponse.id());

        return Response.status(Status.CREATED).entity(res).build();
    }

    @PUT
    @Path("/v1/users/{userId}")
    public Response update(UUID userId, UpdateUserRequest request) {
        userConnector.update(userId, request);

        return Response.noContent().build();
    }
}
