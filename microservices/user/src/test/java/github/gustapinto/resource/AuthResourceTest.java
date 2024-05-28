package github.gustapinto.resource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import github.gustapinto.dto.request.LoginRequest;
import github.gustapinto.service.UserService;

import org.junit.jupiter.api.TestMethodOrder;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class AuthResourceTest {
    static final String USER_EMAIL = "foo@bar.com";
    static final String USER_PASSWORD = "foobar";

    @Inject
    UserService userService;

    @Test
    @Order(1)
    void shouldLoginUser() {
        var userId = userService.create("foo", USER_EMAIL, USER_PASSWORD);

        assertNotNull(userId);

        var body = new LoginRequest(USER_EMAIL, USER_PASSWORD);
        var token = given()
            .contentType(ContentType.JSON)
            .body(body)
            .when()
            .post("/v1/login")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .path("token")
            .toString();

        assertNotNull(token);

        userService.deleteById(userId);
    }
}
