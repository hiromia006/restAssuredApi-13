package click.simple_grocery_store_api;

import click.simple_grocery_store_api.pojo.Product;
import com.thedeanda.lorem.LoremIpsum;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GroceryApiTest extends BaseGroceryApiTest {

    /*
    Get all products
Create a new cart
Add an item to cart
Create a new order
     */

    @Test
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
                "customerName", LoremIpsum.getInstance().getName()
        );

        given()
                .spec(getRequestSpecification())
                .header("Authorization", "Bearer " + registerNewApiClient())
                .body(orderRequest)
                .log().uri()
                .log().body()
                .when()
                .post("/orders")
                .then()
                .statusCode(201)
                .log().body();
    }
}
