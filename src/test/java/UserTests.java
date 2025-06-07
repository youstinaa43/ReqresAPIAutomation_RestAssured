import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class UserTests {
    UserUtils userUtils=new UserUtils();


    String token;

    @BeforeClass
    public void login(){
        Response response=
        JsonPath jsonPath =response.jsonPath();
        token=jsonPath.get("token");

    }
    @Test
    public void getSingleUser(){
        SoftAssert softAssert=new SoftAssert();
        String expectedID="2";
        Response response=RestAssured.given().when().headers(headers).get(baseURL+usersEndPoint+"/2").then().extract().response();
        JsonPath jsonPath=response.jsonPath();
        int actualID=jsonPath.get("data.id");
        Assert.assertEquals(response.statusCode(),200,"Status code is not correct");
        Assert.assertEquals(String.valueOf(actualID),expectedID,"ID is not the same");
        softAssert.assertNotNull(jsonPath.get("data.email"),"email is null");
        softAssert.assertNotNull(jsonPath.get("data.first_name"),"name is null");
        softAssert.assertTrue((jsonPath.getString("data.email")).matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"));
        softAssert.assertTrue((jsonPath.getString("data.avatar")).matches("^https:\\/\\/reqres.in\\/img\\/faces\\/\\d+-image\\.jpg$"));
        softAssert.assertAll();
    }

    @Test
    public void getListOfUsers(){
        Response response= userUtils.getListUsers("2");
        JsonPath jsonPath=response.jsonPath();
        List<String> data =jsonPath.getList("data");
        int actualDataSize=data.size();
        Assert.assertEquals(response.statusCode(),200);

        Assert.assertEquals(jsonPath.getInt("data[0].id"),7);
        int firstID=jsonPath.getInt("data[0].id");
        int lastID=jsonPath.getInt("data[5].id");
        int numberOfUsers=lastID-firstID+1;
        int perPage=jsonPath.getInt("per_page");
        Assert.assertEquals(perPage,numberOfUsers);
        int pageNo=lastID/perPage;
        Assert.assertEquals(jsonPath.getInt("page"),2);
    }
    static String newUserName="morpheus";
    static String job="leader";
    @Test
    public void createNewUser(){
        Response response= RestAssured.given().when().contentType(ContentType.JSON).body("{\n" +
                "    \"name\": \""+newUserName+"\",\n" +
                "    \"job\": \""+job+"\"\n" +
                "}").post(baseURL+usersEndPoint).then().extract().response();
        JsonPath jsonPath=response.jsonPath();
        Assert.assertEquals(response.statusCode(),201);
        Assert.assertEquals(jsonPath.get("name"),newUserName);
        Assert.assertEquals(jsonPath.get("job"),job);
        Assert.assertNotNull(jsonPath.get("id"));
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue((jsonPath.getString("createdAt")).matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z$\n"));

    }
}
