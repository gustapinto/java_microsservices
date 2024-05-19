package github.gustapinto.resource;

import java.util.UUID;

import github.gustapinto.common.dto.response.CreatedResponse;
import github.gustapinto.dto.request.CreateUserRequest;
import github.gustapinto.dto.request.UpdateUserRequest;
import github.gustapinto.dto.response.GetUserResponse;
import github.gustapinto.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path(value = "/v1/users")
public class UserResource {
    @Inject
    UserService userService;

    @POST
    public Response create(CreateUserRequest req) {
        var id = userService.create(req.name(), req.email(), req.password());
        var res = new CreatedResponse(id);

        return Response.status(Status.CREATED).entity(res).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(UUID id, UpdateUserRequest req) {
        userService.updateById(id, req.name());

        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(UUID id) {
        userService.deleteById(id);

        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    public Response get(UUID id) {
        var user = userService.getById(id);
        var res = GetUserResponse.from(user);

        return Response.ok(res).build();
    }
}
