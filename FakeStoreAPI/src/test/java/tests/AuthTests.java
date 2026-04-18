package tests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class AuthTests extends BaseTest {

    @Test
    public void login() {

        given()
                .header("Content-Type","application/json")
                .body("""
                {
                  "username":"mor_2314",
                  "password":"83r5^_"
                }
            """)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200);
    }
}
