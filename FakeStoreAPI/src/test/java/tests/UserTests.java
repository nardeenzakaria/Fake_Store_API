package tests;

import base.BaseTest;
import endpoints.Routes;
import io.restassured.response.Response;
import models.User;
import org.testng.annotations.Test;
import specs.RequestSpecs;
import specs.ResponseSpecs;
import utils.TestDataGenerator;

import static io.restassured.RestAssured.*;

public class UserTests extends BaseTest {

    static int userId;

    @Test
    public void createUser() {

        User user = TestDataGenerator.createUser();

        Response response =

                given()
                        .spec(RequestSpecs.commonRequest())
                        .body(user)

                        .when()
                        .post(Routes.USERS);

        response.then().spec(ResponseSpecs.success200());

        userId = response.jsonPath().getInt("id");
    }
}
