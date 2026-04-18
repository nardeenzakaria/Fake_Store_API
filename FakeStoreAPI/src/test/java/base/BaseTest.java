package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;
import utils.LoggerUtils;

public class BaseTest {

    @BeforeSuite
    public void setup() {

        RestAssured.baseURI =
                ConfigReader.getProperty("baseUrl");

        LoggerUtils.info("Base URI Set Successfully");
    }
}