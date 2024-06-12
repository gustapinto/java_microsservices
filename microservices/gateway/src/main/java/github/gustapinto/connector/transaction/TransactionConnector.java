package github.gustapinto.connector.transaction;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import github.gustapinto.common.dto.response.CreatedResponse;
import github.gustapinto.connector.transaction.dto.CreateTransactionRequest;
import github.gustapinto.connector.transaction.dto.GetTransactionResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@RegisterRestClient
public interface TransactionConnector {
    @POST
    @Path("/v1/users/{userId}/accounts/{accountId}/transactions")
    CreatedResponse create(
        @PathParam("userId") UUID userId,
        @PathParam("accountId") UUID accountId,
        CreateTransactionRequest req
    );

    @GET
    @Path("/v1/users/{userId}/accounts/{accountId}/transactions")
    List<GetTransactionResponse> getAll(
        @PathParam("userId") UUID userId,
        @PathParam("accountId") UUID accountId
    );

    @GET
    @Path("/v1/users/{userId}/accounts/{accountId}/transactions/{transactionId}")
    GetTransactionResponse getById(
        @PathParam("userId") UUID userId,
        @PathParam("accountId") UUID accountId,
        @PathParam("transacionId") UUID transacionId
    );
}
