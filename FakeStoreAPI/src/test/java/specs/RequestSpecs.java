package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecs {

    public static RequestSpecification commonRequest() {

        return new RequestSpecBuilder()
                .setContentType("application/json")
                .build();
    }
}
