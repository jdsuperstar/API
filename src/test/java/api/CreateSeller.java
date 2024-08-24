package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.CustomResponse;
import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.APIRunner;
import utilities.CashWiseToken;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

public class CreateSeller {
@Test
    public void createSeller(){
    String url = Config.getProperty("CashWiseApiUrl");
    String token = CashWiseToken.GetToken();

    RequestBody request = new RequestBody();
    request.setCompany_name("Title1");
    request.setSeller_name("Big Name");
    request.setPhone_number("1231231234");
    request.setAddress("123 Devon");

    Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(request)
            .post(url + "/api/myaccount/sellers");

    System.out.println(response.getStatusCode());
    Map<String , Object> params = new HashMap<>();
    params.put("isArchived" , false);
    params.put("page" , 1);
    params.put("size", 10);
    Response response1 = RestAssured.given().auth().oauth2(token).
            contentType(ContentType.JSON).params(params).get(url+ "/api/myaccount/sellers");
    response1.prettyPrint();



    Map<String , Object> params1 = new HashMap<>();
    params1.put("sellersIdsForArchive" , 5520);
    params1.put("archive" , true);

    Response response2 = RestAssured.given().auth().oauth2(token)
            .params(params1).post(url + "/api/myaccount/sellers/archive/unarchive");
    System.out.println(response2.getStatusCode());

}
@Test
    public void ArchiveAll() throws JsonProcessingException {
    String token = CashWiseToken.GetToken();
    String url  = Config.getProperty("CashWiseApiUrl");

    Map<String , Object> params = new HashMap<>();
    params.put("isArchived" , false);
    params.put("page", 1 );
    params.put("size" , 110);

    Response response = RestAssured.given().auth().oauth2(token).params(params).get(url + "/api/myaccount/sellers");
    int status = response.statusCode();
    Assert.assertEquals(200,status);

    ObjectMapper mapper = new ObjectMapper();
    CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
    String urlToArchive = Config.getProperty("CashWiseApiUrl") + "/api/myaccount/sellers/archive/unarchive";
    int size = customResponse.getResponses().size();
    for(int i =0; i < size; i ++ ) {
        String email = customResponse.getResponses().get(i).getEmail();

        if(email != null && customResponse.getResponses().get(i).getEmail().endsWith("@hotmail.com")){
            int id = customResponse.getResponses().get(i).getSeller_id();
            Map<String , Object> paramsToArchive = new HashMap<>();
            paramsToArchive.put("sellersIdsForArchive", id);
            paramsToArchive.put("archive", true);
            Response response1 = RestAssured.given().auth().oauth2(token).params(paramsToArchive).post(urlToArchive);
            int statusCode = response1.getStatusCode();
            Assert.assertEquals(200, statusCode);

        }
    }

}
@Test
    public void createOneSeller() throws JsonProcessingException {
    String token = CashWiseToken.GetToken();
    String url  = Config.getProperty("CashWiseApiUrl");
    RequestBody request = new RequestBody();
    request.setCompany_name("SuperSellerr");
    request.setSeller_name("Big Seller");
    request.setEmail("superSellerr@hotmail.com");
    request.setPhone_number("1231231234");
    request.setAddress("123 Devon");
    Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(request)
            .post(url + "/api/myaccount/sellers");
    int statusCode = response.getStatusCode();
    Assert.assertEquals(201,statusCode);

    ObjectMapper mapper = new ObjectMapper();
    CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
    int ExpectedSellerId = customResponse.getSeller_id();

    String urlToArchive = Config.getProperty("CashWiseApiUrl") + "/api/myaccount/sellers";


    Map<String , Object> params = new HashMap<>();
    params.put("isArchived" , false);
    params.put("page" , 1);
    params.put("size", 100);



    Response response1 = RestAssured.given().auth().oauth2(token).
            contentType(ContentType.JSON).params(params).get(url+ "/api/myaccount/sellers");
    int statusCode1 = response1.statusCode();
    Assert.assertEquals(200,statusCode1);
    CustomResponse customResponse1 = mapper.readValue(response1.asString() , CustomResponse.class);

    int size = customResponse.getResponses().size();
    boolean isPresent = false;
    for(int i =0; i < size; i++){
        if(customResponse1.getResponses().get(i).getSeller_id() == ExpectedSellerId){
            isPresent = true;
            break;
        }

    }
    Assert.assertTrue(isPresent);

}

@Test
    public void testGEt(){
    APIRunner.runGET("/api/myaccount/sellers/",2344);
    String email = APIRunner.getCustomResponse().getEmail();
    System.out.println(email);
    int id = APIRunner.getCustomResponse().getSeller_id();
    System.out.println(id);
}


}
