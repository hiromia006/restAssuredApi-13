package click.simple_grocery_store_api.pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    @JsonProperty("id")
    public Integer id;

    @JsonProperty("category")
    public String category;

    @JsonProperty("name")
    public String name;

    @JsonProperty("inStock")
    public Boolean inStock;

    public Product() {

    }

    public Integer getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public Boolean getInStock() {
        return inStock;
    }
}