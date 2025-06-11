package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpec {
    public static ResponseSpecification getResponseSpec(int statusCode){
        return new ResponseSpecBuilder().expectContentType(ContentType.JSON)
                .expectStatusCode(statusCode)
                .build();
    }
}
