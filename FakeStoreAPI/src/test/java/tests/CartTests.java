package tests;

import base.BaseTest;
import endpoints.Routes;
import io.restassured.response.Response;
import models.Cart;
import org.testng.Assert;
import org.testng.annotations.Test;
import specs.RequestSpecs;
import specs.ResponseSpecs;
import utils.LoggerUtils;
import utils.TestDataGenerator;

import static io.restassured.RestAssured.*;

public class CartTests extends BaseTest {

    static int cartId;

    @Test(priority = 1)
    public void getAllCarts() {

        LoggerUtils.info("Getting All Carts");

        given()
                .spec(RequestSpecs.commonRequest())

                .when()
                .get(Routes.CARTS)

                .then()
                .spec(ResponseSpecs.success200());

        LoggerUtils.pass("All carts retrieved successfully");
    }

    @Test(priority = 2)
    public void createCart() {

        LoggerUtils.info("Creating New Cart");

        Cart cart = TestDataGenerator.createCart();

        Response response =

                given()
                        .spec(RequestSpecs.commonRequest())
                        .body(cart)

                        .when()
                        .post(Routes.CARTS);

        response.then().spec(ResponseSpecs.success200());

        cartId = response.jsonPath().getInt("id");

        Assert.assertTrue(cartId > 0);

        LoggerUtils.pass("Cart Created Successfully");
    }

    @Test(priority = 3)
    public void getSingleCart() {

        LoggerUtils.info("Getting Single Cart");

        given()
                .spec(RequestSpecs.commonRequest())

                .when()
                .get(Routes.CARTS + "/" + cartId)

                .then()
                .spec(ResponseSpecs.success200());

        LoggerUtils.pass("Single Cart Retrieved");
    }

    @Test(priority = 4)
    public void updateCart() {

        LoggerUtils.info("Updating Cart");

        Cart cart = TestDataGenerator.createCart();
        cart.userId = 5;

        given()
                .spec(RequestSpecs.commonRequest())
                .body(cart)

                .when()
                .put(Routes.CARTS + "/" + cartId)

                .then()
                .spec(ResponseSpecs.success200());

        LoggerUtils.pass("Cart Updated Successfully");
    }

    @Test(priority = 5)
    public void deleteCart() {

        LoggerUtils.info("Deleting Cart");

        given()
                .spec(RequestSpecs.commonRequest())

                .when()
                .delete(Routes.CARTS + "/" + cartId)

                .then()
                .spec(ResponseSpecs.success200());

        LoggerUtils.pass("Cart Deleted Successfully");
    }
}
