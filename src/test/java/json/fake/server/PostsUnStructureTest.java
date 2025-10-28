package json.fake.server;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostsUnStructureTest extends BaseTest {
    @Test
    public void getPostsShouldSucceed() {
        given()
                .spec(getRequestSpecification())
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
                .spec(getRequestSpecification())
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
                .spec(getRequestSpecification())
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
                .spec(getRequestSpecification())
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
                .spec(getRequestSpecification())
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
                .spec(getRequestSpecification())
                .log().uri()
                .when()
                .delete("/posts/1")
                .then()
                .log().body()
                .statusCode(200);
    }
}
