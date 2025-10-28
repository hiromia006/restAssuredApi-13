package json.fake.server;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostsStaticTest {
    @Test
    public void getPostsShouldSucceed() {
        given()
                .baseUri("http://localhost")
                .port(3000)
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void getPostDetailShouldSucceed() {
        given()
                .baseUri("http://localhost")
                .port(3000)
                .log().uri()
                .when()
                .get("/posts/1")
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void postPostShouldSucceed() {
        given()
                .baseUri("http://localhost")
                .port(3000)
                .contentType("application/json")
                .body("{\n" +
                        "\"title\": \"a title Batch-13\",\n" +
                        "\"views\": 100\n" +
                        "}")
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .log().body()
                .statusCode(201);
    }

    @Test
    public void putPostShouldSucceed() {
        given()
                .baseUri("http://localhost")
                .port(3000)
                .body("{\n" +
                        "\"title\": \"put a title Batch-13\",\n" +
                        "\"views\": 200\n" +
                        "}")
                .log().uri()
                .log().body()
                .when()
                .put("/posts/1")
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void patchPostShouldSucceed() {
        given()
                .baseUri("http://localhost")
                .port(3000)
                .body("{\n" +
                        "\"title\": \"Patch a title Batch-13\"\n" +
                        "}")
                .log().uri()
                .log().body()
                .when()
                .put("/posts/1")
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void deletePostShouldSucceed() {
        given()
                .baseUri("http://localhost")
                .port(3000)
                .log().uri()
                .when()
                .delete("/posts/ec91")
                .then()
                .log().body()
                .statusCode(200);
    }
}
