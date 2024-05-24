package github.gustapinto.resource;

import java.util.UUID;

import github.gustapinto.common.dto.response.CreatedResponse;
import github.gustapinto.dto.request.CreateAccountRequest;
import github.gustapinto.dto.request.UpdateAccountRequest;
import github.gustapinto.dto.response.GetAccountResponse;
import github.gustapinto.service.AccountService;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path(value = "/v1/accounts")
public class AccountResource {
    @Inject
    AccountService accountService;

    @POST
    public Response create(CreateAccountRequest req) {
        var id = accountService.create(req.name(), req.initialValue(), req.userId());
        var res = new CreatedResponse(id);

        return Response.status(Status.CREATED).entity(res).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(UUID id, UpdateAccountRequest req) {
        accountService.updateById(id, req.name(), req.currentValue());

        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(UUID id) {
        accountService.deleteById(id);

        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    public Response get(UUID id) {
        var account = accountService.getById(id);
        var res = GetAccountResponse.from(account);

        return Response.ok(res).build();
    }
}
