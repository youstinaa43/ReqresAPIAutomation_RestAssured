import endpoints.LoginEndpoints;
import io.restassured.response.Response;
import models.LoginResponse;
import models.RegisterUserData;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertyUtils;

import java.io.IOException;

public class LoginTests {
    @Test
    public void loginSuccessfully() throws IOException {
        RegisterUserData data=new RegisterUserData();
        data.setEmail(PropertyUtils.get("email"));
        data.setPassword(PropertyUtils.get("password"));
        Response response= LoginEndpoints.successfulLogin(data);
        LoginResponse result=response.as(LoginResponse.class);
        Assert.assertEquals(result.getToken(),PropertyUtils.get("token"));
    }
    @Test
    public void loginUnSuccessfully() throws IOException {
        RegisterUserData data=new RegisterUserData();
        data.setEmail(PropertyUtils.get("email"));
        Response response= LoginEndpoints.unsuccessfulLogin(data);
        LoginResponse result=response.as(LoginResponse.class);
        Assert.assertEquals(result.getError(),"Missing password");
    }


}
