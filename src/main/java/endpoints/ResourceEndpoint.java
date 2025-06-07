package endpoints;

import io.restassured.response.Response;
import specs.RequestSpec;
import specs.ResponseSpec;

import static io.restassured.RestAssured.given;

public class ResourceEndpoint {
    public static Response getResources(){
        return given().spec(RequestSpec.getRequestSpec())
                .when().get(ReqresEndpoints.RESOURCES)
                .then().spec(ResponseSpec.getResponseSpec(200))
                .extract().response();
    }
    public static Response getResource(int resourceID){
        return given().spec(RequestSpec.getRequestSpec())
                .pathParam("id",resourceID)
                .when().get(ReqresEndpoints.RESOURCE_BY_ID)
                .then().spec(ResponseSpec.getResponseSpec(200))
                .extract().response();
    }
    public static Response getResourceNotFound(int resourceID){
        return given().spec(RequestSpec.getRequestSpec())
                .pathParam("id",resourceID)
                .when().get(ReqresEndpoints.RESOURCE_BY_ID)
                .then().spec(ResponseSpec.getResponseSpec(404))
                .extract().response();
    }
}
