//package api;
//
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import org.junit.Assert;
//import utilities.CashWiseToken;
//import utilities.Config;
//
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class Test {
//    @org.junit.Test
////    public void testToken(){
////        String endPoint = "https://backend.cashwise.us/api/myaccount/auth/login";
////        RequestBody requestBody = new RequestBody();
////
////        requestBody.setEmail("isakazy@gmail.com");
////        requestBody.setPassword("isakazyamanbaev");
////
////        Response response =  RestAssured.given().contentType(ContentType.JSON)
////                .body(requestBody).post(endPoint);
////        int statusCode = response.statusCode();
////
////        Assert.assertEquals(200, statusCode);
////       response.prettyPrint();
////       String token = response.jsonPath().getString("jwt_token");
////        System.out.println(token);
////    }
//
//@org.junit.Test
//public void getingleSeller(){
//        String url = Config.getProperty("CashWiseApiUrl") + "/api/myaccount/sellers/" + 4629;
//        String token = CashWiseToken.GetToken();
//
//        Response response = RestAssured.given().auth().oauth2(token).get(url);
//        String expectedEmail = response.jsonPath().getString("email");
//        Assert.assertTrue(expectedEmail.endsWith(".com"));
//        Assert.assertFalse(expectedEmail.isEmpty());
//
//}
//@org.junit.Test
//    public void getAllsellers(){
//        String url = Config.getProperty("CashWiseApiUrl") + "/api/myaccount/sellers";
//        String token = CashWiseToken.GetToken();
//
//    Map<String , Object> params = new HashMap<>();
//    params.put("isArchived",false);
//    params.put("page",1);
//    params.put("size",10);
//
//    Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);
//    int statusCode = response.statusCode();
//    Assert.assertEquals(200,statusCode);
//
//    response.prettyPrint();
//    String email = response.jsonPath().getString("responses[0].email");
//    Assert.assertFalse(email.isEmpty());
//    String email3rd = response.jsonPath().getString("responses[2].email");
//    String email4th = response.jsonPath().getString("responses[3].email");
//    Assert.assertFalse(email3rd.isEmpty());
//    Assert.assertFalse(email4th.isEmpty());
//
//}
//@org.junit.Test
//        public void testGetAllSeller() {
//    String url = Config.getProperty("CashWiseApiUrl") + "/api/myaccount/sellers";
//    String token = CashWiseToken.GetToken();
//
//    Map<String, Object> params = new HashMap<>();
//    params.put("isArchived",false);
//    params.put("page",1);
//    params.put("size",10);
//
//    Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);
//    int statusCode = response.statusCode();
//    Assert.assertEquals(200,statusCode);
//
//    List<String> allEmails = response.jsonPath().getList("responses.email");
//    for(String emails : allEmails){
//        Assert.assertFalse(emails.isEmpty());
//    }
//
//
//
//}
//}
