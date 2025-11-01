package json.fake.server;

import com.thedeanda.lorem.LoremIpsum;
import json.fake.server.pojo.Comment;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostsUsingPojoTest extends BaseTest {
    @Test
    public void getPostsShouldSucceed() {
        List<Comment> commentList = given()
                .spec(getRequestSpecification())
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .log().body()
                .statusCode(200)
                .extract().jsonPath().getList("", Comment.class);

        for (Comment cmt : commentList) {
            System.out.println(cmt.getId() + " " + cmt.getTitle() + " " + cmt.getViews());
        }
    }

    @Test
    public void getPostDetailShouldSucceed() {
        String id = getPostId();

        given()
                .spec(getRequestSpecification())
                .log().uri()
                .when()
                .get("/posts/" + id)
                .then()
                .log().body()
                .statusCode(200)
                .body("id", equalTo(id));
    }

    @Test
    public void getPostDetailShouldFail() {
        given()
                .spec(getRequestSpecification())
                .log().uri()
                .when()
                .get("/posts/sadsadasd")
                .then()
                .log().body()
                .statusCode(404)
                .body(equalTo("Not Found"));

    }

    @Test
    public void postPostShouldSucceed() {
        String title = LoremIpsum.getInstance().getTitle(4);
        int views = (int) (Math.random() * 1000);
        Comment requestBody = new Comment(title, views);

        given()
                .spec(getRequestSpecification())
                .body(requestBody)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .log().body()
                .statusCode(201)
                .body("title", equalTo(title))
                .body("views", equalTo(views));
    }

    @Test
    public void putPostShouldSucceed() {
        String id = getPostId();
        String title = LoremIpsum.getInstance().getTitle(4);
        int views = (int) (Math.random() * 1000);


        given()
                .spec(getRequestSpecification())
                .body(new Comment(title, views))
                .log().uri()
                .log().body()
                .when()
                .put("/posts/" + id)
                .then()
                .log().body()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("title", equalTo(title))
                .body("views", equalTo(views));
    }

    @Test
    public void putPostShouldFail() {
        String title = LoremIpsum.getInstance().getTitle(4);
        int views = (int) (Math.random() * 1000);

        given()
                .spec(getRequestSpecification())
                .body(new Comment(title, views))
                .log().uri()
                .log().body()
                .when()
                .put("/posts/dsadasddasd")
                .then()
                .log().body()
                .statusCode(404)
                .body(equalTo("Not Found"));
    }


    @Test
    public void patchPostShouldSucceed() {
        String id = getPostId();
        int views = (int) (Math.random() * 1000);

        given()
                .spec(getRequestSpecification())
                .body(new Comment(views))
                .log().uri()
                .log().body()
                .when()
                .patch("/posts/" + id)
                .then()
                .log().body()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("views", equalTo(views));
    }

    @Test
    public void patchPostShouldFail() {
        int views = (int) (Math.random() * 1000);
        given()
                .spec(getRequestSpecification())
                .body(new Comment(views))
                .log().uri()
                .log().body()
                .when()
                .put("/posts/dasdasddsa")
                .then()
                .log().body()
                .statusCode(404)
                .body(equalTo("Not Found"));
    }

    @Test
    public void patchPostShouldSucceedV1() {
        String id = getPostId();
        String title = LoremIpsum.getInstance().getTitle(4);

        given()
                .spec(getRequestSpecification())
                .body(new Comment(title))
                .log().uri()
                .log().body()
                .when()
                .put("/posts/" + id)
                .then()
                .log().body()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("title", equalTo(title));
    }


    @Test
    public void patchPostShouldSucceedV2() {
        String id = getPostId();
        int views = (int) (Math.random() * 1000);

        given()
                .spec(getRequestSpecification())
                .body(new Comment(views))
                .log().uri()
                .log().body()
                .when()
                .put("/posts/" + id)
                .then()
                .log().body()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("views", equalTo(views));
    }

    @Test
    public void deletePostShouldSucceed() {
        String id = getPostId();
        given()
                .spec(getRequestSpecification())
                .log().uri()
                .log().body()
                .when()
                .delete("/posts/" + id)
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void deletePostShouldFail() {
        String id = getPostId();
        given()
                .spec(getRequestSpecification())
                .log().uri()
                .log().body()
                .when()
                .delete("/posts/dssdfdsffd" + id)
                .then()
                .log().body()
                .statusCode(404)
                .body(equalTo("Not Found"));
    }
}
