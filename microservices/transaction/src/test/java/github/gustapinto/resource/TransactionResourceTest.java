package github.gustapinto.resource;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import github.gustapinto.dto.request.CreateTransactionRequest;
import github.gustapinto.dto.response.GetTransactionResponse;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;


@QuarkusTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class TransactionResourceTest {
    static String id;
    static UUID userId;

    static final String MOCKED_USER_ID = "678a9b31-9ae1-4a51-8233-536543ac25a0";
    static final String MOCKED_ACCOUNT_ID = "7ed30d36-bc99-4e9d-a390-d0b3630fdefb";

    String baseUrl() {
        return String.format("/v1/users/%s/accounts/%s/transactions", MOCKED_USER_ID, MOCKED_ACCOUNT_ID);
    }

    String baseUrlWithId() {
        return String.format("%s/%s", baseUrl(), id);
    }

    @Test
    @Order(1)
    void shouldCreateTransaction() {
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
        assertEquals(res.accountId().toString(), MOCKED_ACCOUNT_ID);
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
