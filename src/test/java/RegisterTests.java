import endpoints.LoginEndpoints;
import endpoints.RegisterEndpoint;
import io.restassured.response.Response;
import models.LoginResponse;
import models.RegisterResponse;
import models.RegisterUserData;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertyUtils;

import java.io.IOException;

public class RegisterTests {
    @Test
    public void registerSuccessfully() throws IOException {
        RegisterUserData data=new RegisterUserData();
        data.setEmail(PropertyUtils.get("email"));
        data.setPassword(PropertyUtils.get("passwordR"));
        Response response= RegisterEndpoint.successfulRegister(data);
        RegisterResponse result=response.as(RegisterResponse.class);
        Assert.assertEquals(result.getToken(),PropertyUtils.get("token"));
        Assert.assertEquals(result.getId(),Integer.parseInt(PropertyUtils.get("id")));
    }
    @Test
    public void registerUnSuccessfully() throws IOException {
        RegisterUserData data=new RegisterUserData();
        data.setEmail(PropertyUtils.get("email"));
        Response response= RegisterEndpoint.unsuccessfulRegister(data);
        RegisterResponse result=response.as(RegisterResponse.class);
        Assert.assertEquals(result.getError(),PropertyUtils.get("error"));
    }


}
