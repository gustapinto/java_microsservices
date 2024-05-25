package github.gustapinto.resource;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import github.gustapinto.dto.request.CreateUserRequest;
import github.gustapinto.dto.request.UpdateUserRequest;
import github.gustapinto.dto.response.GetUserResponse;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class UserResourceTest {
    static String id;

    String baseUrl() {
        return "/v1/users";
    }

    String baseUrlWithId() {
        return String.format("%s/%s", baseUrl(), id);
    }

    @Test
    @Order(1)
    void shouldCreateUser() {
        var body = new CreateUserRequest(
            "foo",
            "foo@bar.io",
            "12345678"
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
            .as(GetUserResponse.class);

        assertEquals(res.id().toString(), id);
        assertEquals(res.name(), "foo");
        assertEquals(res.email(), "foo@bar.io");
    }

    @Test
    @Order(3)
    void shouldUpdateUser() {
        var body = new UpdateUserRequest(
            "foobar"
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
            .as(GetUserResponse.class);

        assertEquals(res.id().toString(), id);
        assertEquals(res.name(), "foobar");
        assertEquals(res.email(), "foo@bar.io");
    }

    @Test
    @Order(5)
    void shouldDeleteUser() {
        when()
            .delete(baseUrlWithId())
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @Order(6)
    void shouldNotGetUserAfterDelete() {
        when()
            .get(baseUrlWithId())
            .then()
            .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
