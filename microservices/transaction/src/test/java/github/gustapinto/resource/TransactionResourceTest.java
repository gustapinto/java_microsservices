package github.gustapinto.resource;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

import java.util.UUID;

import org.apache.http.HttpStatus;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import github.gustapinto.connector.account.AccountConnector;
import github.gustapinto.connector.account.dto.CreateAccountRequest;
import github.gustapinto.dto.request.CreateTransactionRequest;
import github.gustapinto.dto.response.GetTransactionResponse;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;


@QuarkusTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class TransactionResourceTest {
    @RestClient
    AccountConnector accountConnector;

    static String id;
    static String accountId;
    static UUID userId;

    static final String MOCKED_USER_ID = "678a9b31-9ae1-4a51-8233-536543ac25a0";

    String baseUrl() {
        return String.format("/v1/users/%s/accounts/%s/transactions", MOCKED_USER_ID, accountId);
    }

    String baseUrlWithId() {
        return String.format("%s/%s", baseUrl(), id);
    }

    @Test
    @Order(1)
    void shouldCreateTransaction() {
        var res = accountConnector.create(
            UUID.fromString(MOCKED_USER_ID),
            new CreateAccountRequest("foo", 10)
        );
        accountId = res.id().toString();

        var body = new CreateTransactionRequest(
            "foo",
            10
        );
        id = given()
            .contentType(ContentType.JSON)
            .body(body)
            .when()
            .post(baseUrl())
            .then()
            .statusCode(HttpStatus.SC_CREATED)
            .extract()
            .path("id")
            .toString();
    }

    @Test
    @Order(2)
    void shouldGetTransactionByIdAfterCreate() {
        var res = when()
            .get(baseUrlWithId())
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .as(GetTransactionResponse.class);

        assertEquals(res.id().toString(), id);
        assertEquals(res.userId().toString(), MOCKED_USER_ID);
        assertEquals(res.accountId().toString(), accountId);
        assertEquals(res.name(), "foo");
        assertEquals(res.value(), 10);
        assertNotNull(res.createdAt());
    }

    @Test
    @Order(3)
    void shouldGetAllAfterCreate() {
        var res = when()
            .get(baseUrl())
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .as(GetTransactionResponse[].class);

        assertNotNull(res);
        assertTrue(res.length > 0);
    }

    @Test
    @Order(4)
    void shouldDeleteTransaction() {
        when()
            .delete(baseUrlWithId())
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @Order(5)
    void shouldNotGetTransactionByIdAfterDelete() {
        when()
            .get(baseUrlWithId())
            .then()
            .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
