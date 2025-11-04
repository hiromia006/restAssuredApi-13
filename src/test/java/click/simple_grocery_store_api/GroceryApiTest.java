package click.simple_grocery_store_api;

import click.simple_grocery_store_api.pojo.Product;
import com.thedeanda.lorem.LoremIpsum;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GroceryApiTest extends BaseGroceryApiTest {
    String accessToken = "0511a065f23cd1aad05fa0b2b435d3952425b8c9d3cccd46a306d6f911515fcd";
    /*
    Get all products
Create a new cart
Add an item to cart
Create a new order
     */

    @Test(priority = 0)
    public void placeOrderShouldSucceed() {

        List<Product> productList = given()
                .spec(getRequestSpecification())
                .log().uri()
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("", Product.class);

        String cartId = given()
                .spec(getRequestSpecification())
                .log().uri()
                .when()
                .post("/carts")
                .then()
                .statusCode(201)
                .log().body()
                .extract().jsonPath().getString("cartId");

        Map<String, Integer> itemToAdd = Map.of(
                "productId", productList.get(0).getId(),
                "quantity", 2
        );

        given()
                .spec(getRequestSpecification())
                .body(itemToAdd)
                .log().uri()
                .log().body()
                .when()
                .post("/carts/{cartId}/items", cartId)
                .then()
                .statusCode(201)
                .log().body();

        Map<String, String> orderRequest = Map.of(
                "cartId", cartId,
                "customerName", LoremIpsum.getInstance().getName(),
                "comment", LoremIpsum.getInstance().getTitle(15)
        );

        given()
                .spec(getRequestSpecification())
//                .header("Authorization", "Bearer " + registerNewApiClient())
                .header("Authorization", "Bearer " + accessToken)
                .body(orderRequest)
                .log().uri()
                .log().body()
                .when()
                .post("/orders")
                .then()
                .statusCode(201)
                .log().body();
    }

    @Test(priority = 1)
    public void getOrdersShouldSucceed() {
        given()
                .spec(getRequestSpecification())
                .header("Authorization", "Bearer " + accessToken)
                .log().uri()
                .when()
                .get("/orders")
                .then()
                .statusCode(200)
                .log().body();
    }


    @Test(priority = 2)
    public void getOrderDetailsShouldSucceed() {
        String orderId = getOderId();

        given()
                .spec(getRequestSpecification())
                .header("Authorization", "Bearer " + accessToken)
                .log().uri()
                .when()
                .get("/orders/" + orderId)
                .then()
                .log().body()
                .statusCode(200);

    }

    @Test(priority = 2)
    public void patchOrderShouldSucceed() {
        String orderId = getOderId();

        Map<String, String> orderRequest = Map.of(
                "customerName", LoremIpsum.getInstance().getName(),
                "comment", LoremIpsum.getInstance().getTitle(15)
        );

        given()
                .spec(getRequestSpecification())
//                .header("Authorization", "Bearer " + registerNewApiClient())
                .header("Authorization", "Bearer " + accessToken)
                .body(orderRequest)
                .log().uri()
                .log().body()
                .when()
                .patch("/orders/" + orderId)
                .then()
                .log().body()
                .statusCode(204);


    }


    @Test(priority = 4)
    public void deleteOrderShouldSucceed() {
        String orderId = getOderId();


        given()
                .spec(getRequestSpecification())
//                .header("Authorization", "Bearer " + registerNewApiClient())
                .header("Authorization", "Bearer " + accessToken)
                .log().uri()
                .when()
                .delete("/orders/" + orderId)
                .then()
                .log().body()
                .statusCode(204);


    }

    public String getOderId() {
        return given()
                .spec(getRequestSpecification())
                .header("Authorization", "Bearer " + accessToken)
                .log().uri()
                .when()
                .get("/orders")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("[0].id");
    }
}
