package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.CustomResponse;
import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Data;
import lombok.Getter;
import org.checkerframework.checker.units.qual.C;

import java.util.Map;

@Data
public class APIRunner {
    @Getter
    private static CustomResponse customResponse;

    //GET API for all
    public static void runGET(String path , int id){
        String token = CashWiseToken.GetToken();
        String url = Config.getProperty("CashWiseApiUrl") + path +id;
        Response response = RestAssured.given().auth().oauth2(token).get(url);
        System.out.println("Status code: " + response.getStatusCode());
        ObjectMapper mapper = new ObjectMapper();
        try{
            customResponse = mapper.readValue(response.asString() , CustomResponse.class);
        } catch (JsonProcessingException e) {
            System.out.println("This is a List response");
        }
    }

    //Get API with params
    public static void runGet(String path, Map<String , Object> params)  {
        String token = CashWiseToken.GetToken();
        String url = Config.getProperty("CashWiseApiUrl") + path;
        Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);
        System.out.println("Status code: " + response.getStatusCode());
        ObjectMapper mapper = new ObjectMapper();
        try{
            customResponse = mapper.readValue(response.asString() , CustomResponse.class);
        }
         catch (JsonProcessingException e) {
            System.out.println("This is a single response");
        }
    }

        //POST API with requestBody
    public static void runPOST(String path , RequestBody requestBody){
        String token = CashWiseToken.GetToken();
        String url = Config.getProperty("CashWiseApiUrl") + path ;
        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON)
                .body(requestBody).post(url);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("Status code: " + response.getStatusCode());
        try{
            customResponse = mapper.readValue(response.asString() , CustomResponse.class);
        }
        catch (JsonProcessingException e) {
            System.out.println("This is a single response");
        }
    }


        //POST API with parameters
    public static void runPOST(String path , Map <String , Object> params){
        String token = CashWiseToken.GetToken();
        String url = Config.getProperty("CashWiseApiUrl") + path ;
        Response response = RestAssured.given().auth().oauth2(token).params(params).post(url);
        System.out.println("Status code: " + response.getStatusCode());
        ObjectMapper mapper = new ObjectMapper();
        try{
            customResponse = mapper.readValue(response.asString() , CustomResponse.class);
        }
        catch (JsonProcessingException e) {
            System.out.println("This is a single response");
        }
    }
        //DELETE API
    public static void runDELETE (String path){
        String token = CashWiseToken.GetToken();
        String url = Config.getProperty("CashWiseApiUrl") + path ;
        Response response = RestAssured.given().auth().oauth2(token).delete(url);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("Status code: " + response.getStatusCode());
        try{
            customResponse = mapper.readValue(response.asString() , CustomResponse.class);
        }
        catch (JsonProcessingException e) {
            System.out.println("This is a single response");
        }
    }
    //PUT with request body
    public static void renPUT(String path , RequestBody requestBody , int id){
        String token = CashWiseToken.GetToken();
        String url = Config.getProperty("CashWiseApiUrl") + path + id;
        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON)
                .body(requestBody).put(url);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("Status code: " + response.getStatusCode());
        try{
            customResponse = mapper.readValue(response.asString() , CustomResponse.class);
        }
        catch (JsonProcessingException e) {
            System.out.println("This is a single response");
        }

    }





}