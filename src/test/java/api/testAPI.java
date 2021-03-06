package api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class testAPI {

    @Test
    public void
    test_api_create_account() {
        given()
                .contentType("application/json")
                .body("{\"accountId\" : 10, \"userId\" : 1, \"balance\" : 0}")
                .when()
                .post("/accounts")
                .then()
                .statusCode(201);

        get("/accounts/10").then().statusCode(200).body("data.accountId", equalTo(10),"data.balance" , equalTo(0));
    }

    @Test
    public void
    test_api_transfer() {
        given()
                .contentType("application/json")
                .body("{\"accountIdFrom\": 1, \"accountIdTo\": 2,\"amount\": \"5\"}")
                .when()
                .post("/accounts/transfer")
                .then()
                .statusCode(200);

        get("/accounts/2").then().statusCode(200).body("data.accountId", equalTo(2),"data.balance" , equalTo(105));
    }
}
