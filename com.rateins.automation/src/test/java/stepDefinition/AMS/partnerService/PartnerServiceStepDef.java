package stepDefinition.AMS.partnerService;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import utils.TestInitialize;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PartnerServiceStepDef {
	public static WebDriver driver;
	public static Properties prop;
	protected ValidatableResponse validatableResponse;
	protected String partnerServiceEndPt = "https://qa.nonprod.rateins.com";
	protected String partnerServicetokenEndPt = "https://gr.oktapreview.com/oauth2/ausvtetp64jEl2AuE0h7/v1/token";
	protected String client_id = System.getenv("AUTOMATION_LEAD_SERVICE_CLIENT_ID");
	protected String client_secret = System.getenv("AUTOMATION_LEAD_SERVICE_CLIENT_SECRET");
	protected String apikey;
	protected String token;
	protected JSONObject object1 = new JSONObject();
	protected JSONObject object2 = new JSONObject();
    
	public PartnerServiceStepDef()
	{
		prop = TestInitialize.getProperties();
	}
  	
    @Given("a valid partner id")
    public void a_valid_partnerid(DataTable APIData){
    	List<Map<String, String>> partnerdetails = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = partnerdetails.get(0);
		partnerServiceEndPt = partnerServiceEndPt + "/api/partners/" + dataRow.get("partnerid");
        System.out.println("AMS Partner Service Endpoint :"+partnerServiceEndPt);
    }
    
	@And("a valid authentication bearer token")
	public void a_valid_authentication_bearer_token() {
		token= 
				given()
                .formParam("client_id", client_id)
                .formParam("client_secret", client_secret)
                .formParam("grant_type", "client_credentials").log().all()
                .when()
                .post(partnerServicetokenEndPt)
                .then().log().all().extract().path("access_token");
      
		System.out.println("Token: "+ token);
	}
    
    @When("I send a GET request to fetch the partner details with valid partner id")
    public void I_send_a_GET_request_to_fetch_the_partner_details_with_valid_partner_id(){
    	validatableResponse = 
	        given()
	        .header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
	        .when()
	        .get(partnerServiceEndPt)
	        .then().log().all();
    	
    }

    @Then("validate the response of the GET request for partner details with valid partner id")
    public void validate_the_response_of_the_GET_request_for_partner_details_with_valid_partner_id(DataTable APIData){
    	List<Map<String, String>> status_code = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = status_code.get(0);
        validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
    }
    
    @And("a invalid authentication bearer token")
	public void a_invalid_authentication_bearer_token() {
		token= "invalidbearerTokencode";
		System.out.println("Token: "+ token);
	}
    
    @When("I send a GET request to fetch the partner details with invalid token")
    public void I_send_a_GET_request_to_fetch_the_partner_details_with_invalid_token(){
    	validatableResponse = 
    	        given()
    	        .header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
    	        .when()
    	        .get(partnerServiceEndPt)
    	        .then().log().all();
    	
    }

    @Then("validate the response of the GET request for partner details with invalid token")
    public void validate_the_response_of_the_GET_request_for_partner_details_with_invalid_token(DataTable APIData){
    	List<Map<String, String>> status_code = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = status_code.get(0);
        validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
        validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
    }
    
    @Given("an invalid partner id")
    public void an_invalid_partnerid(DataTable APIData){
    	List<Map<String, String>> partnerdetails = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = partnerdetails.get(0);
		partnerServiceEndPt = partnerServiceEndPt + "/api/partners/" + dataRow.get("partnerid");
        System.out.println("AMS Partner Service Endpoint :"+partnerServiceEndPt);
    }
	
    @When("I send a GET request to fetch the partner details with invalid partner id")
    public void I_send_a_GET_request_to_fetch_the_partner_details_with_invalid_partnerid(){
    	validatableResponse = 
    	        given()
    	        .header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
    	        .when()
    	        .get(partnerServiceEndPt)
    	        .then().log().all();
    	
    }

    @Then("validate the response of the GET request for partner details with invalid partner id")
    public void validate_the_response_of_the_GET_request_for_partner_details_with_invalid_partnerid(DataTable APIData){
    	List<Map<String, String>> status_code = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = status_code.get(0);
        validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
        validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
    }
    
    @Given("a valid api key")
    public void a_valid_apikey(DataTable APIData){
    	List<Map<String, String>> apikeyvalue = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apikeyvalue.get(0);
		apikey = dataRow.get("apikey");
        System.out.println("XAPI key is :"+apikey);
        partnerServiceEndPt = partnerServiceEndPt + "/api/partners/auth";
        System.out.println("AMS Partner Service Endpoint :"+partnerServiceEndPt);
    }
    
    @When("I send a GET request to fetch the partner details with valid api key")
    public void I_send_a_GET_request_to_fetch_the_partner_details_with_valid_api_key(){
    	validatableResponse = 
	        given()
	        .headers("Authorization", "Bearer " + token,"X-API-Key", apikey)
	        .contentType(ContentType.JSON)
	        .when()
	        .get(partnerServiceEndPt)
	        .then().log().all();
    	
    }

    @Then("validate the response of the GET request for partner details with valid api key")
    public void validate_the_response_of_the_GET_request_for_partner_details_with_valid_api_key(DataTable APIData){
    	List<Map<String, String>> status_code = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = status_code.get(0);
        validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
    }
    
}