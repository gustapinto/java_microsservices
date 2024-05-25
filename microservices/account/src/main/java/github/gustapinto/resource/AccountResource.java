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

@Path("/v1")
public class AccountResource {
    @Inject
    AccountService accountService;

    @POST
    @Path("/users/{userId}/accounts")
    public Response create(UUID userId, CreateAccountRequest req) {
        var id = accountService.create(req.name(), req.initialValue(), userId);
        var res = new CreatedResponse(id);

        return Response.status(Status.CREATED).entity(res).build();
    }

    @PUT
    @Path("/users/{userId}/accounts/{accountId}")
    public Response update(UUID userId, UUID accountId, UpdateAccountRequest req) {
        accountService.updateById(accountId, userId, req.name(), req.currentValue());

        return Response.noContent().build();
    }

    @DELETE
    @Path("/users/{userId}/accounts/{accountId}")
    public Response delete(UUID userId, UUID accountId) {
        accountService.deleteById(accountId, userId);

        return Response.noContent().build();
    }

    @GET
    @Path("/users/{userId}/accounts")
    public Response getAll(UUID userId) {
        var transactions = accountService.getAll(userId);
        var res = GetAccountResponse.from(transactions);

        return Response.ok(res).build();
    }

    @GET
    @Path("/users/{userId}/accounts/{accountId}")
    public Response getById(UUID userId, UUID accountId) {
        var account = accountService.getById(accountId, userId);
        var res = GetAccountResponse.from(account);

        return Response.ok(res).build();
    }
}
