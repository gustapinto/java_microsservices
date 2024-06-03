package github.gustapinto.connector.account;

import java.util.UUID;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import github.gustapinto.common.dto.response.CreatedResponse;
import github.gustapinto.connector.account.dto.CreateAccountRequest;
import github.gustapinto.connector.account.dto.GetAccountResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@RegisterRestClient
public interface AccountConnector {
    @POST
    @Path("/v1/users/{userId}/accounts")
    CreatedResponse create(@PathParam("userId") UUID userId, CreateAccountRequest request);

    @GET
    @Path("/v1/users/{userId}/accounts/{accountId}")
    GetAccountResponse getById(@PathParam("userId") UUID userId, @PathParam("accountId") UUID accountId);
}
