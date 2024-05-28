package github.gustapinto.resource;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import github.gustapinto.dto.request.CreateAccountRequest;
import github.gustapinto.dto.request.UpdateAccountRequest;
import github.gustapinto.dto.response.GetAccountResponse;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;


@QuarkusTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class AccountResourceTest {
    static String id;

    static final String MOCKED_USER_ID = "678a9b31-9ae1-4a51-8233-536543ac25a0";

    String baseUrl() {
        return String.format("/v1/users/%s/accounts", MOCKED_USER_ID);
    }

    String baseUrlWithId() {
        return String.format("%s/%s", baseUrl(), id);
    }

    @Test
    @Order(1)
    void shouldCreateUser() {
        var body = new CreateAccountRequest(
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
    void shouldGetUserAfterCreate() {
        var res = when()
            .get(baseUrlWithId())
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .as(GetAccountResponse.class);

        assertEquals(res.id().toString(), id);
        assertEquals(res.name(), "foo");
        assertEquals(res.initialValue(), 10);
        assertEquals(res.currentValue(), 10);
        assertEquals(res.userId().toString(), MOCKED_USER_ID);
    }

    @Test
    @Order(3)
    void shouldUpdateUser() {
        var body = new UpdateAccountRequest(
            "foobar",
            15
        );
        given()
            .contentType(ContentType.JSON)
            .body(body)
            .when()
            .put(baseUrlWithId())
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @Order(4)
    void shouldGetUserAfterUpdate() {
        var res = when()
            .get(baseUrlWithId())
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .as(GetAccountResponse.class);

        assertEquals(res.id().toString(), id);
        assertEquals(res.name(), "foobar");
        assertEquals(res.initialValue(), 10);
        assertEquals(res.currentValue(), 15);
        assertEquals(res.userId().toString(), MOCKED_USER_ID);
    }

    @Test
    @Order(5)
    void shouldGetAllUserAfterUpdate() {
        var res = when()
            .get(baseUrl())
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .as(GetAccountResponse[].class);

        assertNotNull(res);
        assertTrue(res.length > 0);
    }

    @Test
    @Order(6)
    void shouldDeleteUser() {
        when()
            .delete(baseUrlWithId())
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @Order(7)
    void shouldNotGetUserAfterDelete() {
        when()
            .get(baseUrlWithId())
            .then()
            .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
