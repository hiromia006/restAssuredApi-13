package json.fake.server;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseTest {

    public RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(3000)
                .setContentType("application/json")
                .build();
    }

    public String getPostId() {
        return given()
                .spec(getRequestSpecification())
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("[0].id");
    }

    public Map<String, Object> getJsonBodyV2(String title, int views) {
        return Map.of(
                "title", title,
                "views", views
        );

    }

    public Map<String, Object> getJsonBodyV1(String title, int views) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", title);
        requestBody.put("views", views);

        return requestBody;
    }

    public Map<String, Object> getJsonBodyV1(int views) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("views", views);
        return requestBody;
    }

    public Map<String, Object> getJsonBodyV1(String title) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", title);
        return requestBody;
    }
}
