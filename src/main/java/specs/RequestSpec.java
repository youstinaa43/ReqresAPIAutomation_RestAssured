package specs;

import config.APIConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;

public class RequestSpec {
    public static RequestSpecification getRequestSpec() throws IOException {
        return new RequestSpecBuilder()
                .setBaseUri(APIConfig.getBaseURL())
                .addHeader("Content-Type","application/json")
                .addHeader("Accept","*/*")
                .addHeader("x-api-key","reqres-free-v1")
                .build();
    }
}
