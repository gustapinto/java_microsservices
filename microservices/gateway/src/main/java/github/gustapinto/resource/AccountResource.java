package github.gustapinto.resource;

import java.util.UUID;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import github.gustapinto.connector.account.AccountConnector;
import github.gustapinto.connector.account.dto.CreateAccountRequest;
import github.gustapinto.connector.transaction.TransactionConnector;
import github.gustapinto.connector.transaction.dto.CreateTransactionRequest;
import github.gustapinto.dto.response.GetAccountWithTransactionsResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("")
public class AccountResource {
    @RestClient
    AccountConnector accountConnector;

    @RestClient
    TransactionConnector transactionConnector;

    @Inject
    @Claim
    UUID userId;

    @POST
    @Path("/v1/accounts")
    public Response createAccount(CreateAccountRequest req) {
        var createdResponse = accountConnector.create(userId, req);
        var res = accountConnector.getById(userId, createdResponse.id());

        return Response.status(Status.CREATED).entity(res).build();
    }

    @GET
    @Path("/v1/accounts/{accountId}")
    public Response getById(UUID accountId) {
        var account = accountConnector.getById(userId, accountId);
        var transactions = transactionConnector.getAll(userId, accountId);
        var res = GetAccountWithTransactionsResponse.from(account, transactions);

        return Response.ok(res).build();
    }

    @POST
    @Path("/v1/accounts/{accountId}/transactions")
    public Response createTransaction(UUID accountId, CreateTransactionRequest req) {
        var createdResponse = transactionConnector.create(userId, accountId, req);
        var res = transactionConnector.getById(userId, accountId, createdResponse.id());


        return Response.status(Status.CREATED).entity(res).build();
    }
}
