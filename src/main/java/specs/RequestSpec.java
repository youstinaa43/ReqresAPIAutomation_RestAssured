package specs;

import config.APIConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {
    public static RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(APIConfig.getBaseURL())
                .addHeader("Content-Type","application/json")
                .addHeader("Accept","*/*")
                .build();
    }
}
