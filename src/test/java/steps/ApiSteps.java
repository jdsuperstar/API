package steps;

import com.github.javafaker.Faker;
import entities.RequestBody;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.hu.Ha;
import org.junit.Assert;
import utilities.APIRunner;

import java.util.HashMap;
import java.util.Map;

public class ApiSteps {
    Faker faker = new Faker();
    String email;
    String sellerName;
    int sellerId;
    @Given("User hits get single seller api with {string}")
    public void user_hits_get_single_seller_api_with(String endPoint) {
        APIRunner.runGET(endPoint , 5726);
        sellerId = APIRunner.getCustomResponse().getSeller_id();
    }
    @Then("verify seller email is not empty")
    public void verify_seller_email_is_not_empty() {
        String email = APIRunner.getCustomResponse().getEmail();
        Assert.assertFalse(email.isEmpty());
    }
    @Given("User hit get get all seller api with {string}")
    public void user_hit_get_all_seller_api_with(String endPoint) {
        Map<String , Object> params = new HashMap<>();
        params.put("isArchived" , false);
        params.put("page" , 1);
        params.put("size" , 110);
        APIRunner.runGet(endPoint, params);
    }
    @Then("verify seller ids are not equal to {int}")
    public void verify_seller_ids_are_not_equal_to(int int1) {
       int size = APIRunner.getCustomResponse().getResponses().size();
       for(int i = 0; i < size; i++){
           int sellerid = APIRunner.getCustomResponse().getResponses().get(i).getSeller_id();
           Assert.assertNotEquals(int1 , sellerid);
       }
    }
    @Then("user hit put api with {string}")
    public void user_hit_put_api_with(String endPoint) {
        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name(faker.company().name());
        requestBody.setSeller_name(faker.name().firstName());
        requestBody.setPhone_number(faker.phoneNumber().phoneNumber());
        requestBody.setEmail(faker.internet().emailAddress());
        APIRunner.renPUT(endPoint ,requestBody ,5726);
        email = APIRunner.getCustomResponse().getEmail();
        sellerName = APIRunner.getCustomResponse().getSeller_name();
    }
    @Then("verify user email was updated")
    public void verify_user_email_was_updated() {
       Assert.assertFalse(email.isEmpty());
    }
    @Then("verify user first name was updated")
    public void verify_user_first_name_was_updated() {
     Assert.assertFalse(sellerName.isEmpty());
    }

    @Then("User hits archive api with {string}")
    public void user_hits_archive_api_with(String endPoint) {
        Map<String,Object>params=new HashMap<>();
        params.put("sellersIdsForArchive", sellerId);
        params.put("archive" , true);
        APIRunner.runPOST(endPoint , params);

    }
    @Then("User hit get all unarchive seller api with {string}")
    public void user_hit_get_all_unarchive_seller_api_with(String endPoint) {
    Map<String , Object> params = new HashMap<>();
    params.put("isArchived" , true);
    params.put("page" , 1) ;
    params.put("size" , 110);
    APIRunner.runGet(endPoint , params);
    boolean isPresent =  false;

    int size = APIRunner.getCustomResponse().getResponses().size();
    for(int i = 0; i < size; i++){
        int ids = APIRunner.getCustomResponse().getResponses().get(i).getSeller_id();
        if(sellerId == ids){
            isPresent = true;
            break;
        }
    }
    Assert.assertTrue(isPresent);
    }

    @Given("user hit post api with {string}")
    public void user_hit_post_api_with(String endpoint) {
        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name("whatever");
        requestBody.setSeller_name("whateveName");
        requestBody.setEmail(faker.internet().emailAddress());
        requestBody.setPhone_number("1231231234");
        requestBody.setAddress("2343 devon");
        APIRunner.runPOST(endpoint , requestBody);
        sellerId = APIRunner.getCustomResponse().getSeller_id();
        sellerName = APIRunner.getCustomResponse().getSeller_name();


    }
    @Then("verify seller id was generated")
    public void verify_seller_id_was_generated() {
      Assert.assertTrue(sellerId != 0);

    }
    @Then("verify seller name is not empty")
    public void verify_seller_name_is_not_empty() {
      Assert.assertFalse(sellerName.isEmpty());
    }
    @Then("delete the seller with {string}")
    public void delete_the_seller_with(String endpoint) {
        APIRunner.runDELETE(endpoint + sellerId);
    }
    @Then("verify deleted seller is not on the list")
    public void verify_deleted_seller_is_not_on_the_list() {
      boolean isEmpty = true;
      int size = APIRunner.getCustomResponse().getResponses().size();
      for(int i =0; i <size; i++){
          int id =APIRunner.getCustomResponse().getResponses().get(i).getSeller_id();
          if(id != sellerId){
              isEmpty = false;
              break;
          }
      }Assert.assertFalse(isEmpty);
    }





}