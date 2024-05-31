package github.gustapinto.connector.user;

import java.util.UUID;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import github.gustapinto.common.dto.response.CreatedResponse;
import github.gustapinto.connector.user.dto.CreateUserRequest;
import github.gustapinto.connector.user.dto.LoginRequest;
import github.gustapinto.connector.user.dto.LoginResponse;
import github.gustapinto.connector.user.dto.UpdateUserRequest;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@RegisterRestClient(configKey = "user-connector")
public interface UserConnector {
    @POST
    @Path("/v1/users")
    CreatedResponse create(CreateUserRequest request);

    @PUT
    @Path("/v1/users/{userId}")
    void update(@PathParam("userId") UUID userId, UpdateUserRequest request);

    @POST
    @Path("/v1/auth/login")
    LoginResponse login(LoginRequest request);
}
