import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class AuthIntegrationTest {
    @BeforeAll
    static void setUp(){
        RestAssured.baseURI = "http://localhost:4004"; // gateway address

    }

    @Test
    public void shouldReturnOKWithValidToken(){
        // STEPS are 3 to be performed
        // 1 Arrange - making arrangements like setting up db, defining payloads etc
        // 2 Act - code that triggers teh calling of actual functions API to get result
        // 3 Assert - assert that response we get is valid and as per our requirement

        String loginPayload = """
                {
                    "email" : "testuser@test.com",
                    "password" : "password123"
                }
                """;
        Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .log().all()
                .statusCode(200)
                .body("token", notNullValue())
                .extract()
                .response();

        System.out.println("Generated token : " + response.jsonPath().getString("token"));
    }

    @Test
    public void shouldReturnUnauthorizedOnInvalidLogin(){
        // STEPS are 3 to be performed
        // 1 Arrange - making arrangements like setting up db, defining payloads etc
        // 2 Act - code that triggers teh calling of actual functions API to get result
        // 3 Assert - assert that response we get is valid and as per our requirement

        String loginPayload = """
                {
                    "email" : "testuserinvalidone@test.com",
                    "password" : "password123"
                }
                """;
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .log().all()
                .statusCode(401);
    }
}
