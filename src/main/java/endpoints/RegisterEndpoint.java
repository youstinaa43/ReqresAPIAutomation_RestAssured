package endpoints;

import io.restassured.response.Response;
import models.RegisterUserData;
import specs.RequestSpec;
import specs.ResponseSpec;

import static io.restassured.RestAssured.given;

public class RegisterEndpoint {
    public static Response successfulRegister(RegisterUserData payload){
        return given().spec(RequestSpec.getRequestSpec())
                .body(payload)
                .when().post(ReqresEndpoints.REGISTER)
                .then().spec(ResponseSpec.getResponseSpec(200))
                .extract().response();
    }
    public static Response unsuccessfulRegister(RegisterUserData payload){
        return given().spec(RequestSpec.getRequestSpec())
                .body(payload)
                .when().post(ReqresEndpoints.REGISTER)
                .then().spec(ResponseSpec.getResponseSpec(400))
                .extract().response();
    }
}
