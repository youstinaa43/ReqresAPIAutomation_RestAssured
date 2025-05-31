package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class UserUtils {
    static String usersEndPoint="/users";
    static String baseURL="https://reqres.in/api";

    public Response getListUsers(String pageNo){
        Map<String,String> queryParams=new HashMap<>();
        queryParams.put("page",pageNo);
        return RestAssured.given().queryParams(queryParams).get(baseURL+usersEndPoint).then().extract().response();

    }
}
