package github.gustapinto.connector.account;

import java.util.UUID;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import github.gustapinto.connector.account.dto.GetAccountResponse;
import github.gustapinto.connector.account.dto.UpdateAccountRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@RegisterRestClient
public interface AccountConnector {
    @GET
    @Path("/v1/users/{userId}/accounts/{accountId}")
    GetAccountResponse getById(
        @PathParam("userId") UUID userId,
        @PathParam("accountId") UUID accountId
    );

    @PUT
    @Path("/v1/users/{userId}/accounts/{accountId}")
    void updateById(
        @PathParam("userId") UUID userId,
        @PathParam("accountId") UUID accountId,
        UpdateAccountRequest request
    );
}
