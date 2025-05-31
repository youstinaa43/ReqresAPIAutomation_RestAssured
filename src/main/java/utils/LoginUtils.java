package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static utils.UserUtils.baseURL;

public class LoginUtils {
    static String userName="eve.holt@reqres.in";
    static String password="cityslicka";
    static String baseURL="https://reqres.in/api";
    static String loginEndPoint="/login";
    public Response login(){
        return RestAssured.given().when().contentType(ContentType.JSON).body("{\n" +
                "    \"email\": \""+userName+"\",\n" +
                "    \"password\": \""+password+"\"\n" +
                "}").post(baseURL+loginEndPoint).then().extract().response();
    }
}
