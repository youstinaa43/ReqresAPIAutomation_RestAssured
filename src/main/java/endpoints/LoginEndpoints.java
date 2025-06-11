package endpoints;
import config.APIConfig;
import io.restassured.response.Response;
import models.RegisterUserData;
import specs.RequestSpec;
import specs.ResponseSpec;
import java.io.IOException;
import static io.restassured.RestAssured.given;

public class LoginEndpoints {
    public static Response successfulLogin(RegisterUserData payload) throws IOException {
        return given().spec(RequestSpec.getRequestSpec())
                .body(payload)
                .when().post(ReqresEndpoints.LOGIN)
                .then().spec(ResponseSpec.getResponseSpec(200))
                .extract().response();
    }
    public static Response unsuccessfulLogin(RegisterUserData payload) throws IOException {
        return given().spec(RequestSpec.getRequestSpec())
                .body(payload)
                .when().post(ReqresEndpoints.LOGIN)
                .then().spec(ResponseSpec.getResponseSpec(400))
                .extract().response();
    }
}
