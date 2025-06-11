import endpoints.ResourceEndpoint;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import models.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.PropertyUtils;

import java.io.IOException;
import java.util.List;

public class ResourcesTests {
    @Test
    public void getSingleResource() throws IOException {
        SoftAssert softAssert=new SoftAssert();
        int resourceNo=Integer.parseInt(PropertyUtils.get("resourceNo"));
        Response response= ResourceEndpoint.getResource(resourceNo);
        SingleResourceResponse result=response.as(SingleResourceResponse.class);
        ResourceDataResponse data=result.getData();
        Assert.assertEquals(data.getId(),resourceNo,"resource No is not the same");
        softAssert.assertEquals(data.getName(),PropertyUtils.get("resourceName"));
        Assert.assertEquals(data.getYear(),Integer.parseInt(PropertyUtils.get("year")));
        softAssert.assertEquals(data.getColor(),PropertyUtils.get("color"));
        softAssert.assertEquals(data.getPantone_value(),PropertyUtils.get("pantone_value"));
        Support support= result.getSupport();
        softAssert.assertEquals(support.getText(),PropertyUtils.get("supportText"));
        softAssert.assertEquals(support.getUrl(),PropertyUtils.get("supportUrl"));
        softAssert.assertAll();
    }
    @Test
    public void getWrongResource() throws IOException {
        int wrongResourceNo=Integer.parseInt(PropertyUtils.get("wrongResourceNo"));
        Response response= ResourceEndpoint.getResourceNotFound(wrongResourceNo);
        Assert.assertEquals(response.statusCode(),404);
    }

    @Test
    public void getListOfResources() throws IOException {
        SoftAssert softAssert=new SoftAssert();
        Response response= ResourceEndpoint.getResources();
        ListResourceResponse result=response.as(ListResourceResponse.class);
        List<ResourceDataResponse> data =result.getData();
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
        for(ResourceDataResponse user:data){
            softAssert.assertNotNull(user.getName());
            softAssert.assertNotNull(user.getYear());
            softAssert.assertNotNull(user.getId());
            softAssert.assertNotNull(user.getColor());
            softAssert.assertNotNull(user.getPantone_value());
        }
        Support support= result.getSupport();
        softAssert.assertEquals(support.getText(),PropertyUtils.get("supportText"));
        softAssert.assertEquals(support.getUrl(),PropertyUtils.get("supportUrl"));
        softAssert.assertAll();
    }


}
