package api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class testAPI {

    @Test
    public void
    test_api_1_create_account() {
        given()
                .contentType("application/json")
                .body("{\"accountId\" : 4, \"userId\" : 1, \"balance\" : 0}")
                .when()
                .post("/accounts")
                .then()
                .statusCode(201);

        get("/accounts/4").then().statusCode(200);
//                body("data.accountId", equalTo(2));
    }
}
