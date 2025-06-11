package endpoints;

import io.restassured.response.Response;
import models.UserData;
import specs.RequestSpec;
import specs.ResponseSpec;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class UserEndpoints {
    public static Response getUsers(int page) throws IOException {
        return given().spec(RequestSpec.getRequestSpec())
                .queryParam("page",page)
                .when().get(ReqresEndpoints.USERS)
                .then().spec(ResponseSpec.getResponseSpec(200))
                .extract().response();
    }
    public static Response getUser(int userID) throws IOException {
        return given().spec(RequestSpec.getRequestSpec())
                .pathParam("id",userID)
                .when().get(ReqresEndpoints.USER_BY_ID)
                .then().spec(ResponseSpec.getResponseSpec(200))
                .extract().response();
    }
    public static Response getUserNotFound(int userID) throws IOException {
        return given().spec(RequestSpec.getRequestSpec())
                .pathParam("id",userID)
                .when().get(ReqresEndpoints.USER_BY_ID)
                .then().spec(ResponseSpec.getResponseSpec(404))
                .extract().response();
    }
    public static Response createUser(UserData payLoad) throws IOException {
        return given().spec(RequestSpec.getRequestSpec())
                .body(payLoad)
                .when().post(ReqresEndpoints.USERS)
                .then().spec(ResponseSpec.getResponseSpec(201))
                .extract().response();
    }
    public static Response updateUser(UserData payLoad,int userID) throws IOException {
        return given().spec(RequestSpec.getRequestSpec())
                .pathParam("id",userID)
                .body(payLoad)
                .when().put(ReqresEndpoints.USER_BY_ID)
                .then().spec(ResponseSpec.getResponseSpec(200))
                .extract().response();
    }
    public static Response patchUser(UserData payLoad,int userID) throws IOException {
        return given().spec(RequestSpec.getRequestSpec())
                .pathParam("id",userID)
                .body(payLoad)
                .when().patch(ReqresEndpoints.USER_BY_ID)
                .then().spec(ResponseSpec.getResponseSpec(200))
                .extract().response();
    }
    public static Response deleteUser(int userID) throws IOException {
        return given().spec(RequestSpec.getRequestSpec())
                .pathParam("id",userID)
                .when().delete(ReqresEndpoints.USER_BY_ID)
                .then().statusCode(204)
                .extract().response();
    }
    public static Response delayResponse(int delay) throws IOException {
        return given().spec(RequestSpec.getRequestSpec())
                .queryParam("delay",delay)
                .when().get(ReqresEndpoints.USERS)
                .then().spec(ResponseSpec.getResponseSpec(200))
                .extract().response();
    }
}
