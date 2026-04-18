package tests;

import base.BaseTest;
import endpoints.Routes;
import io.restassured.response.Response;
import models.Product;
import org.testng.Assert;
import org.testng.annotations.Test;
import specs.RequestSpecs;
import specs.ResponseSpecs;
import utils.TestDataGenerator;

import static io.restassured.RestAssured.*;

public class ProductTests extends BaseTest {

    static int productId;

    @Test(priority = 1)
    public void getAllProducts() {

        given()
                .spec(RequestSpecs.commonRequest())

                .when()
                .get(Routes.PRODUCTS)

                .then()
                .spec(ResponseSpecs.success200());
    }

    @Test(priority = 2)
    public void createProduct() {

        Product product = TestDataGenerator.createProduct();

        Response response =

                given()
                        .spec(RequestSpecs.commonRequest())
                        .body(product)

                        .when()
                        .post(Routes.PRODUCTS);

        response.then().spec(ResponseSpecs.success200());

        productId = response.jsonPath().getInt("id");

        Assert.assertTrue(productId > 0);
    }

    @Test(priority = 3)
    public void getSingleProduct() {

        given()
                .spec(RequestSpecs.commonRequest())

                .when()
                .get(Routes.PRODUCTS + "/" + productId)

                .then()
                .spec(ResponseSpecs.success200());
    }

    @Test(priority = 4)
    public void updateProduct() {

        Product product = TestDataGenerator.createProduct();
        product.title = "Updated Product";

        given()
                .spec(RequestSpecs.commonRequest())
                .body(product)

                .when()
                .put(Routes.PRODUCTS + "/" + productId)

                .then()
                .spec(ResponseSpecs.success200());
    }

    @Test(priority = 5)
    public void deleteProduct() {

        given()
                .spec(RequestSpecs.commonRequest())

                .when()
                .delete(Routes.PRODUCTS + "/" + productId)

                .then()
                .spec(ResponseSpecs.success200());
    }
}
