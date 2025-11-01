package click.simple_grocery_store_api;

import com.thedeanda.lorem.LoremIpsum;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseGroceryApiTest {

    public RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri("https://simple-grocery-store-api.click")
                .setContentType("application/json")
                .build();
    }


    public String registerNewApiClient() {
        Map<String, String> requestBody = Map.of(
                "clientName", LoremIpsum.getInstance().getName(),
                "clientEmail", LoremIpsum.getInstance().getEmail()
        );

        return given()
                .spec(getRequestSpecification())
                .body(requestBody)
                .log().uri()
                .log().body()
                .when()
                .post("/api-clients")
                .then()
                .statusCode(201)
                .log().body()
                .extract().jsonPath().getString("accessToken");
    }
}
