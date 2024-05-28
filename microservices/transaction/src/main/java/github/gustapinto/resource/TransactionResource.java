package github.gustapinto.resource;

import java.util.UUID;

import github.gustapinto.common.dto.response.CreatedResponse;
import github.gustapinto.dto.request.CreateTransactionRequest;
import github.gustapinto.dto.response.GetTransactionResponse;
import github.gustapinto.service.TransactionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("")
public class TransactionResource {
    @Inject
    TransactionService transactionService;

    @POST
    @Path("/v1/users/{userId}/accounts/{accountId}/transactions")
    public Response create(UUID userId, UUID accountId, CreateTransactionRequest req) {
        var id = transactionService.create(req.name(), req.value(), userId, accountId);
        var res = new CreatedResponse(id);

        return Response.status(Status.CREATED).entity(res).build();
    }

    @DELETE
    @Path("/v1/users/{userId}/accounts/{accountId}/transactions/{transactionId}")
    public Response delete(UUID userId, UUID accountId, UUID transactionId) {
        transactionService.deleteById(transactionId, userId, accountId);

        return Response.noContent().build();
    }

    @GET
    @Path("/v1/users/{userId}/accounts/{accountId}/transactions")
    public Response getAll(UUID userId, UUID accountId) {
        var transactions = transactionService.getAll(userId, accountId);
        var res = GetTransactionResponse.from(transactions);

        return Response.ok(res).build();
    }

    @GET
    @Path("/v1/users/{userId}/accounts/{accountId}/transactions/{transactionId}")
    public Response getById(UUID userId, UUID accountId, UUID transactionId) {
        var transaction = transactionService.getById(transactionId, userId, accountId);
        var res = GetTransactionResponse.from(transaction);

        return Response.ok(res).build();
    }
}
