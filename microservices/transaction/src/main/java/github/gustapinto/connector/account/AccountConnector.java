package github.gustapinto.connector.account;

import java.util.UUID;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import github.gustapinto.common.dto.response.CreatedResponse;
import github.gustapinto.connector.account.dto.CreateAccountRequest;
import github.gustapinto.connector.account.dto.GetAccountResponse;
import github.gustapinto.connector.account.dto.UpdateAccountRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/")
@RegisterRestClient(configKey = "account-connector")
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

    @POST
    @Path("/v1/users/{userId}/accounts")
    CreatedResponse create(
        @PathParam("userId") UUID userId,
        CreateAccountRequest request
    );
}
