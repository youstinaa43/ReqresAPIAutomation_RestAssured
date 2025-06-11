import endpoints.UserEndpoints;
import io.restassured.response.Response;
import models.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.PropertyUtils;

import java.io.IOException;
import java.util.List;

public class UserTests {
    @Test
    public void getSingleUser() throws IOException {
        SoftAssert softAssert=new SoftAssert();
        int userNo=Integer.parseInt(PropertyUtils.get("userNo"));
        Response response= UserEndpoints.getUser(userNo);
        SingleUserResponse result=response.as(SingleUserResponse.class);
        UserDataResponse data= result.getData();
        Assert.assertEquals(data.getId(),userNo,"user No is not the same");
        softAssert.assertNotNull(data.getEmail(),"email is null");
        softAssert.assertNotNull(data.getFirst_name(),"name is null");
        softAssert.assertEquals(data.getFirst_name(),PropertyUtils.get("firstName"));
        softAssert.assertEquals(data.getLast_name(),PropertyUtils.get("lastName"));
        softAssert.assertTrue(data.getEmail().matches(PropertyUtils.get("emailRegex")));
        softAssert.assertTrue((data.getAvatar().matches(PropertyUtils.get("avatarRegex"))));
        Support support= result.getSupport();
        softAssert.assertEquals(support.getText(),PropertyUtils.get("supportText"));
        softAssert.assertEquals(support.getUrl(),PropertyUtils.get("supportUrl"));
        softAssert.assertAll();
    }
    @Test
    public void getWrongUser() throws IOException {
        int userNo=Integer.parseInt(PropertyUtils.get("wrongUserNo"));
        Response response= UserEndpoints.getUserNotFound(userNo);
        Assert.assertEquals(response.statusCode(),404);
    }

    @Test
    public void getListOfUsers() throws IOException {
        SoftAssert softAssert=new SoftAssert();
        Response response= UserEndpoints.getUsers(Integer.parseInt(PropertyUtils.get("pageNo")));
        ListUsersResponse result=response.as(ListUsersResponse.class);
        List<UserDataResponse> data =result.getData();
        Assert.assertEquals(data.get(0).getId(),7);
        int firstID=data.get(0).getId();
        int lastID=data.get(5).getId();
        int numberOfUsers=lastID-firstID+1;
        int perPage=result.getPer_page();
        softAssert.assertEquals(perPage,numberOfUsers);
        softAssert.assertEquals(data.size(),perPage);
        int pageNo=lastID/perPage;
        softAssert.assertEquals(pageNo,Integer.parseInt(PropertyUtils.get("pageNo")));
        Assert.assertEquals(result.getTotal_pages(),2);
        for(UserDataResponse user:data){
            softAssert.assertTrue(user.getEmail().matches(PropertyUtils.get("emailRegex")));
            softAssert.assertTrue((user.getAvatar().matches(PropertyUtils.get("avatarRegex"))));
        }
        Support support= result.getSupport();
        softAssert.assertEquals(support.getText(),PropertyUtils.get("supportText"));
        softAssert.assertEquals(support.getUrl(),PropertyUtils.get("supportUrl"));
        softAssert.assertAll();
    }

    @Test
    public void createNewUser() throws IOException {
        UserData userData=new UserData();
        userData.setName(PropertyUtils.get("newUserName"));
        userData.setJob(PropertyUtils.get("job"));
        Response response= UserEndpoints.createUser(userData);
        UserOperationResponse result=response.as(UserOperationResponse.class);
        Assert.assertEquals(result.getName(),PropertyUtils.get("newUserName"));
        Assert.assertEquals(result.getJob(),PropertyUtils.get("job"));
        Assert.assertNotNull(result.getId());
        Assert.assertTrue(result.getCreatedAt().matches(PropertyUtils.get("createdAtRegex")));

    }

    @Test
    public void upDateUser() throws IOException {
        UserData userData=new UserData();
        userData.setName(PropertyUtils.get("nameU"));
        userData.setJob(PropertyUtils.get("jobU"));
        Response response= UserEndpoints.updateUser(userData,Integer.parseInt(PropertyUtils.get("userNo")));
        UserOperationResponse result=response.as(UserOperationResponse.class);
        Assert.assertEquals(result.getName(),PropertyUtils.get("nameU"));
        Assert.assertEquals(result.getJob(),PropertyUtils.get("jobU"));
        Assert.assertTrue(result.getUpdatedAt().matches(PropertyUtils.get("updateAtRegex")));

    }

    @Test
    public void updateUserUsingPatch() throws IOException {
        UserData userData=new UserData();
        userData.setName(PropertyUtils.get("nameU"));
        userData.setJob(PropertyUtils.get("jobU"));
        Response response= UserEndpoints.patchUser(userData,Integer.parseInt(PropertyUtils.get("userNo")));
        UserOperationResponse result=response.as(UserOperationResponse.class);
        Assert.assertEquals(result.getName(),PropertyUtils.get("nameU"));
        Assert.assertEquals(result.getJob(),PropertyUtils.get("jobU"));
        Assert.assertTrue(result.getUpdatedAt().matches(PropertyUtils.get("updateAtRegex")));
    }

    @Test
    public void delete_User() throws IOException {
        Response response= UserEndpoints.deleteUser(Integer.parseInt(PropertyUtils.get("userNo")));
        Assert.assertEquals(response.statusCode(),204);
    }

    @Test
    public void getListOfUsersDelay() throws IOException {
        SoftAssert softAssert=new SoftAssert();
        Response response= UserEndpoints.delayResponse(Integer.parseInt(PropertyUtils.get("delay")));
        ListUsersResponse result=response.as(ListUsersResponse.class);
        List<UserDataResponse> data =result.getData();
        Assert.assertEquals(data.get(0).getId(),1);
        int firstID=data.get(0).getId();
        int lastID=data.get(5).getId();
        int numberOfUsers=lastID-firstID+1;
        int perPage=result.getPer_page();
        softAssert.assertEquals(perPage,numberOfUsers);
        softAssert.assertEquals(data.size(),perPage);
        int pageNo=lastID/perPage;
        softAssert.assertEquals(pageNo,1);
        Assert.assertEquals(result.getTotal_pages(),2);
        for(UserDataResponse user:data){
            softAssert.assertTrue(user.getEmail().matches(PropertyUtils.get("emailRegex")));
            softAssert.assertTrue((user.getAvatar().matches(PropertyUtils.get("avatarRegex"))));
        }
        Support support= result.getSupport();
        softAssert.assertEquals(support.getText(),PropertyUtils.get("supportText"));
        softAssert.assertEquals(support.getUrl(),PropertyUtils.get("supportUrl"));
        softAssert.assertAll();
    }



}
