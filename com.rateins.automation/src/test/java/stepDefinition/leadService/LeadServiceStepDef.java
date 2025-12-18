package stepDefinition.leadService;

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

public class LeadServiceStepDef {
	public static WebDriver driver;
	public static Properties prop;
		
	protected ValidatableResponse validatableResponse;
	protected String leadServiceEndPt = "https://api-qa.nonprod.rateins.com/leads";
	protected String leadServicetokenEndPt = "https://gr.oktapreview.com/oauth2/ausvtetp64jEl2AuE0h7/v1/token";
	protected String client_id = System.getenv("AUTOMATION_LEAD_SERVICE_CLIENT_ID");
	protected String client_secret = System.getenv("AUTOMATION_LEAD_SERVICE_CLIENT_SECRET");
	protected String token;
	protected JSONObject object1 = new JSONObject();
	protected JSONObject object2 = new JSONObject();
    
	public LeadServiceStepDef()
	{
		prop = TestInitialize.getProperties();
	}
  	
    @Given("a lead")
    public void a_lead(DataTable APIData){
    	List<Map<String, String>> leadId = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = leadId.get(0);
		leadServiceEndPt = leadServiceEndPt + "?lead_id=" + dataRow.get("id");
        System.out.println("Lead Service Endpoint :"+leadServiceEndPt);
    }
    
    @When("I send a request to the URL")
    public void sendRequest(){
    	validatableResponse = 
	        given()
	        .header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
	        .when()
	        .get(leadServiceEndPt)
	        .then().log().all();
    	
    }

    @Then("I validate the status response code")
    public void verifyStatusResponseCode(DataTable APIData){
    	List<Map<String, String>> status_code = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = status_code.get(0);
        validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
    }
    
	@Then("validate the response of the endpoint with status code")
	public void validate_the_response_of_the_endpoint_with_status_code(DataTable APIData) {
			List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
			Map<String, String> dataRow = apiData.get(0);
	        validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	        validatableResponse.assertThat().body("message",equalTo(dataRow.get("message")));
	        validatableResponse.assertThat().body("lead.id",equalTo(dataRow.get("id")));
	        validatableResponse.assertThat().body("lead.data.source",equalTo(dataRow.get("source")));
	        validatableResponse.assertThat().body("lead.data.externalId",equalTo(dataRow.get("externalId")));
	}
	
    @Given("the user details")
    public void the_user_details(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("first_name", dataRow.get("firstname"));
		object1.put("last_name", dataRow.get("lastname"));
		object1.put("record_type_name", dataRow.get("recordtypename"));
    	object2.put("source", dataRow.get("source"));
    	object2.put("external_id", dataRow.get("externalId"));
    	object1.put("data", object2);
		System.out.println(object1);
        System.out.println(object2);
    }
    
    @When("I send a post request with the user details")
    public void I_send_a_post_request_with_the_user_details(){
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(leadServiceEndPt)
	        .then()
	               .assertThat().statusCode(201).log().all();
    }
    
	@Then("I validate the response of the post call")
	public void I_validate_the_response_of_the_post_call(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("message",equalTo(dataRow.get("message")));
        validatableResponse.assertThat().body("leadMatch.matchAction",equalTo(dataRow.get("matchAction")));
        validatableResponse.assertThat().body("leadMatch.matchObjectType",equalTo(dataRow.get("matchObjectType")));
	}
	
	@And("close the session")
	public void close_the_session() {
		driver.quit();
	}
    
    @Given("a valid lead id")
    public void a_valid_lead_id(DataTable APIData){
    	a_token();
    	List<Map<String, String>> leadId = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = leadId.get(0);
		object1.put("lead_id", dataRow.get("id"));
		given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).when().get(leadServiceEndPt + "?lead_id=" + dataRow.get("id")).then().assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
        System.out.println("Lead Service Endpoint :"+leadServiceEndPt + "?lead_id=" + dataRow.get("id"));
    }
    
    @When("I send a patch request with the lead details")
    public void I_send_a_patch_request_with_the_lead_details(){
    	validatableResponse = 
	        given()
			       .header("Authorization", "Bearer " + token)       
			       .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .patch(leadServiceEndPt)
	        .then()
	               .assertThat().statusCode(200).log().all();
    }
	
	@Then("I validate the response of the patch call")
	public void I_validate_the_response_of_the_patch_call(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("message",equalTo(dataRow.get("message")));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
        validatableResponse.assertThat().body("grinsLeadId",equalTo(dataRow.get("id")));
	}
    
    @Given("the driver details")
    public void the_driver_details(DataTable driverData){
    	List<Map<String, String>> data = driverData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("first_name", dataRow.get("firstname"));
		object1.put("last_name", dataRow.get("lastname"));
		object1.put("date_of_birth", dataRow.get("dateofbirth"));
		object1.put("gender", dataRow.get("gender"));
		System.out.println(object1);
        System.out.println("Driver details are " + dataRow.get("firstname") + " "+ dataRow.get("lastname") + " "+ dataRow.get("dateofbirth") + " "+ dataRow.get("gender"));
    }
	
    @When("I send a post request with the driver details")
    public void I_send_a_post_request_with_the_driver_details(){
    	String driverendPoint = "/d23599b6-89be-4b32-8c9c-ac4d8e0b355c/driver";
    	validatableResponse = 
        given()
		       .header("Authorization", "Bearer " + token)       
		       .contentType(ContentType.JSON)
               .body(object1.toString())
               .log().all()
        .when()
              .post(leadServiceEndPt + driverendPoint)
        .then()
               .assertThat().statusCode(200).log().all();
    }
    
	@Then("I validate the response of the post call for driver object")
	public void I_validate_the_response_of_the_post_call_for_driver_object(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("message",equalTo(dataRow.get("message")));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
    
	@And("a token")
	public void a_token() {
		token= 
				given()
                .formParam("client_id", client_id)
                .formParam("client_secret", client_secret)
                .formParam("grant_type", "client_credentials").log().all()
                .when()
                .post(leadServicetokenEndPt)
                .then().log().all().extract().path("access_token");
      
		System.out.println("Token: "+ token);
	}
	
	@And("an invalid token")
	public void an_invalid_token() {
		token= "invalidTokencode";
		System.out.println("Token: "+ token);
	}
	
    @Given("the vehicle details")
    public void the_vehicle_details(DataTable vehicleData){
    	List<Map<String, String>> data = vehicleData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("year", Integer.parseInt(dataRow.get("year")));
		object1.put("make", dataRow.get("make"));
		object1.put("model", dataRow.get("model"));
		object1.put("sub_model_trim", dataRow.get("sub_model_trim"));
		object1.put("vin", dataRow.get("vin"));
		object1.put("vehicle_ownership", dataRow.get("vehicle_ownership"));
		object1.put("vehicle_usage", dataRow.get("vehicle_usage"));
		object1.put("annual_mileage", dataRow.get("annual_mileage"));
		System.out.println(object1);
    }
	
    @When("I send a post request with the vehicle details")
    public void I_send_a_post_request_with_the_vehicle_details(){
    	String vehicleendPoint = "/d23599b6-89be-4b32-8c9c-ac4d8e0b355c/vehicle";
    	validatableResponse = 
        given()
		       .header("Authorization", "Bearer " + token)       
		       .contentType(ContentType.JSON)
               .body(object1.toString())
               .log().all()
        .when()
              .post(leadServiceEndPt + vehicleendPoint)
        .then()
               .assertThat().statusCode(200).log().all();
    }
	
	@Then("I validate the response of the post call for vehicle object")
	public void I_validate_the_response_of_the_post_call_for_vehicke_object(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("message",equalTo(dataRow.get("message")));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}

	@When("I send a post request with invalid user details")
    public void I_send_a_post_request_with_invalid_user_details(){
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(leadServiceEndPt)
	        .then()
	               .assertThat().statusCode(400).log().all();
    }
	
    @Then("validate the response of the post call with invalid user details")
	public void validate_the_response_of_the_post_call_with_invalid_user_details(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
 
    @Given("a invalid lead id")
    public void a_invalid_lead_id(DataTable APIData){
    	a_token();
    	List<Map<String, String>> leadId = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = leadId.get(0);
		object1.put("lead_id", dataRow.get("id"));
		given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).when().get(leadServiceEndPt + "?lead_id=" + dataRow.get("id")).then().assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
        System.out.println("Lead Service Endpoint :"+leadServiceEndPt + "?lead_id=" + dataRow.get("id"));
    }
    
    @When("I send a patch request with the invalid lead details")
    public void I_send_a_patch_request_with_the_invalid_lead_details(){
    	validatableResponse = 
	        given()
			       .header("Authorization", "Bearer " + token)       
			       .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .patch(leadServiceEndPt)
	        .then()
	               .assertThat().statusCode(500).log().all();
    }
    
	@Then("validate the response of the patch call with invalid lead details")
	public void validate_the_response_of_the_patch_call_with_invalid_lead_details(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
    @When("I send a post request with the invalid driver details")
    public void I_send_a_post_request_with_the_invalid_driver_details(){
    	String driverendPoint = "/d23599b6-89be-4b32-8c9c-ac4d8e0b355c/driver";
    	validatableResponse = 
        given()
		       .header("Authorization", "Bearer " + token)       
		       .contentType(ContentType.JSON)
               .body(object1.toString())
               .log().all()
        .when()
              .post(leadServiceEndPt + driverendPoint)
        .then()
               .assertThat().statusCode(400).log().all();
    }
	
	@Then("I validate the response of the post call for invalid driver object")
	public void I_validate_the_response_of_the_post_call_for_invalid_driver_object(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
    @When("I send a post request with the invalid vehicle details")
    public void I_send_a_post_request_with_the_invalid_vehicle_details(){
    	String vehicleendPoint = "/d23599b6-89be-4b32-8c9c-ac4d8e0b355c/vehicle";
    	validatableResponse = 
        given()
		       .header("Authorization", "Bearer " + token)       
		       .contentType(ContentType.JSON)
               .body(object1.toString())
               .log().all()
        .when()
              .post(leadServiceEndPt + vehicleendPoint)
        .then()
               .assertThat().statusCode(400).log().all();
    }
	
	@Then("I validate the response of the post call for invalid vehicle object")
	public void I_validate_the_response_of_the_post_call_for_invalid_vehicke_object(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
    @Then("I validate the response with invalid token")
    public void verifyStatusResponseCodeinvalidToken(DataTable APIData){
    	List<Map<String, String>> status_code = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = status_code.get(0);
        validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
        validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
    }
    
    @Given("the invalid first name of the user")
    public void the_invalid_first_name_of_the_user(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("first_name", dataRow.get("firstname"));
		object1.put("last_name", dataRow.get("lastname"));
		object1.put("record_type_name", dataRow.get("recordtypename"));
    	object2.put("source", dataRow.get("source"));
    	object2.put("external_id", dataRow.get("externalId"));
    	object1.put("data", object2);
		System.out.println(object1);
        System.out.println(object2);
    }
	
	@When("I send a post request with invalid token details")
    public void I_send_a_post_request_with_invalid_token_details(){
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(leadServiceEndPt)
	        .then()
	               .assertThat().statusCode(401).log().all();
    }
    
    @Then("validate the response of the post call with invalid token details")
    public void verifyStatusResponseCodeinvalidTokenPostCall(DataTable APIData){
    	List<Map<String, String>> status_code = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = status_code.get(0);
        validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
        validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
    }

    @Given("a blank source")
    public void a_blank_source(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("first_name", dataRow.get("firstname"));
		object1.put("last_name", dataRow.get("lastname"));
		object1.put("record_type_name", dataRow.get("recordtypename"));
    	object2.put("source", dataRow.get("source"));
    	object2.put("external_id", dataRow.get("externalId"));
    	object1.put("data", object2);
		System.out.println(object1);
        System.out.println(object2);
    }
    
	@When("I send a post request with invalid source details")
    public void I_send_a_post_request_with_invalid_source_details(){
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(leadServiceEndPt)
	        .then()
	               .assertThat().statusCode(400).log().all();
    }
	
    @Then("validate the response of the post call with invalid source details")
	public void validate_the_response_of_the_post_call_with_invalid_source_details(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
    
    @Given("the invalid external id")
    public void the_invalid_external_id(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("first_name", dataRow.get("firstname"));
		object1.put("last_name", dataRow.get("lastname"));
		object1.put("record_type_name", dataRow.get("recordtypename"));
    	object2.put("source", dataRow.get("source"));
    	object2.put("external_id", dataRow.get("externalId"));
    	object1.put("data", object2);
		System.out.println(object1);
        System.out.println(object2);
    }
    
	@When("I send a post request with invalid external id")
    public void I_send_a_post_request_with_invalid_external_id(){
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(leadServiceEndPt)
	        .then()
	               .assertThat().statusCode(400).log().all();
    }
	
    @Then("validate the response of the post call with invalid external id")
	public void validate_the_response_of_the_post_call_with_invalid_external_id(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
    
	@When("I send a post request with invalid recordtypename")
    public void I_send_a_post_request_with_invalid_recordtypename(){
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(leadServiceEndPt)
	        .then()
	               .assertThat().statusCode(400).log().all();
    }
	
    @Then("validate the response of the post call with invalid recordtypename")
	public void validate_the_response_of_the_post_call_with_invalid_recordtypename(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
    
    @When("I send a patch request with the invalid token details")
    public void I_send_a_patch_request_with_the_invalid_token_details(){
    	validatableResponse = 
	        given()
			       .header("Authorization", "Bearer " + token)       
			       .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .patch(leadServiceEndPt)
	        .then()
	               .assertThat().statusCode(401).log().all();
    }
    
	@Then("validate the response of the patch call with invalid token details")
	public void validate_the_response_of_the_patch_call_with_invalid_token_details(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
    
    @When("I send a post request with all the valid driver details and invalid token")
    public void I_send_a_post_request_with_all_the_valid_driver_details_and_invalid_token(DataTable APIData){
    	List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		String driverendPoint = dataRow.get("id");
    	validatableResponse = 
        given()
		       .header("Authorization", "Bearer " + token)       
		       .contentType(ContentType.JSON)
               .body(object1.toString())
               .log().all()
        .when()
              .post(leadServiceEndPt +"/" +driverendPoint+"/driver")
              
        .then()
               .assertThat().statusCode(401).log().all();
    }
    
	@Then("I validate the response of the post call for driver object with invalid token")
	public void I_validate_the_response_of_the_post_call_for_driver_object_with_invalid_token(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@When("I send a post request with all the valid driver details and invalid last name")
    public void I_send_a_post_request_with_all_the_valid_driver_details_and_invalid_last_name(DataTable APIData){
    	List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		String driverendPoint = dataRow.get("id");
    	validatableResponse = 
        given()
		       .header("Authorization", "Bearer " + token)       
		       .contentType(ContentType.JSON)
               .body(object1.toString())
               .log().all()
        .when()
              .post(leadServiceEndPt +"/" +driverendPoint+"/driver")
              
        .then()
               .log().all();
    }
    
	@Then("I validate the response of the post call for driver object with invalid last name")
	public void I_validate_the_response_of_the_post_call_for_driver_object_with_invalid_last_name(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@When("I send a post request with all the valid driver details and invalid DOB")
    public void I_send_a_post_request_with_all_the_valid_driver_details_and_invalid_DOB(DataTable APIData){
    	List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		String driverendPoint = dataRow.get("id");
    	validatableResponse = 
        given()
		       .header("Authorization", "Bearer " + token)       
		       .contentType(ContentType.JSON)
               .body(object1.toString())
               .log().all()
        .when()
              .post(leadServiceEndPt +"/" +driverendPoint+"/driver")
              
        .then()
               .log().all();
    }
    
	@Then("I validate the response of the post call for driver object with invalid DOB")
	public void I_validate_the_response_of_the_post_call_for_driver_object_with_invalid_DOB(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@When("I send a post request with all the valid driver details and invalid Gender")
    public void I_send_a_post_request_with_all_the_valid_driver_details_and_invalid_Gender(DataTable APIData){
    	List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		String driverendPoint = dataRow.get("id");
    	validatableResponse = 
        given()
		       .header("Authorization", "Bearer " + token)       
		       .contentType(ContentType.JSON)
               .body(object1.toString())
               .log().all()
        .when()
              .post(leadServiceEndPt +"/" +driverendPoint+"/driver")

        .then()
               .log().all();
    }

	@Then("I validate the response of the post call for driver object with invalid Gender")
	public void I_validate_the_response_of_the_post_call_for_driver_object_with_invalid_Gender(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
    @When("I send a post request with the vehicle details with invalid token")
    public void I_send_a_post_request_with_the_vehicle_details_with_invalid_token(DataTable APIData){
    	List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		String vehicleendPoint = dataRow.get("id");
    	validatableResponse = 
        given()
		       .header("Authorization", "Bearer " + token)       
		       .contentType(ContentType.JSON)
               .body(object1.toString())
               .log().all()
        .when()
              .post(leadServiceEndPt +"/" +vehicleendPoint+"/vehicle")
              
        .then()
               .log().all();
    }
     
	@Then("I validate the response of the post call for vehicle object with invalid token")
	public void I_validate_the_response_of_the_post_call_for_vehicle_object_with_invalid_token(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	 
    @Given("the vehicle details without year")
    public void the_vehicle_details_without_year(DataTable vehicleData){
    	List<Map<String, String>> data = vehicleData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("make", dataRow.get("make"));
		object1.put("model", dataRow.get("model"));
		object1.put("sub_model_trim", dataRow.get("sub_model_trim"));
		object1.put("vin", dataRow.get("vin"));
		object1.put("vehicle_ownership", dataRow.get("vehicle_ownership"));
		object1.put("vehicle_usage", dataRow.get("vehicle_usage"));
		object1.put("annual_mileage", dataRow.get("annual_mileage"));
		System.out.println(object1);
    }
	
    @When("I send a post request with the vehicle details with invalid year")
    public void I_send_a_post_request_with_the_vehicle_details_with_invalid_year(DataTable APIData){
    	List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		String vehicleendPoint = dataRow.get("id");
    	validatableResponse = 
        given()
		       .header("Authorization", "Bearer " + token)       
		       .contentType(ContentType.JSON)
               .body(object1.toString())
               .log().all()
        .when()
              .post(leadServiceEndPt +"/" +vehicleendPoint+"/vehicle")
              
        .then()
               .log().all();
    }
	
	@Then("I validate the response of the post call for vehicle object with invalid year")
	public void I_validate_the_response_of_the_post_call_for_vehicle_object_with_invalid_year(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
    @When("I send a post request with the vehicle details with invalid make")
    public void I_send_a_post_request_with_the_vehicle_details_with_invalid_make(DataTable APIData){
    	List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		String vehicleendPoint = dataRow.get("id");
    	validatableResponse = 
        given()
		       .header("Authorization", "Bearer " + token)       
		       .contentType(ContentType.JSON)
               .body(object1.toString())
               .log().all()
        .when()
              .post(leadServiceEndPt +"/" +vehicleendPoint+"/vehicle")
              
        .then()
               .log().all();
    }
	
	@Then("I validate the response of the post call for vehicle object with invalid make")
	public void I_validate_the_response_of_the_post_call_for_vehicle_object_with_invalid_make(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
    @When("I send a post request with the vehicle details with invalid model")
    public void I_send_a_post_request_with_the_vehicle_details_with_invalid_model(DataTable APIData){
    	List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		String vehicleendPoint = dataRow.get("id");
    	validatableResponse = 
        given()
		       .header("Authorization", "Bearer " + token)       
		       .contentType(ContentType.JSON)
               .body(object1.toString())
               .log().all()
        .when()
              .post(leadServiceEndPt +"/" +vehicleendPoint+"/vehicle")
              
        .then()
               .log().all();
    }
	
	@Then("I validate the response of the post call for vehicle object with invalid model")
	public void I_validate_the_response_of_the_post_call_for_vehicle_object_with_invalid_model(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@When("I send a post request with the vehicle details with invalid sub-model")
    public void I_send_a_post_request_with_the_vehicle_details_with_invalid_sub_model(DataTable APIData){
    	List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		String vehicleendPoint = dataRow.get("id");
    	validatableResponse = 
        given()
		       .header("Authorization", "Bearer " + token)       
		       .contentType(ContentType.JSON)
               .body(object1.toString())
               .log().all()
        .when()
              .post(leadServiceEndPt +"/" +vehicleendPoint+"/vehicle")
              
        .then()
               .log().all();
    }
	
	@Then("I validate the response of the post call for vehicle object with invalid sub-model")
	public void I_validate_the_response_of_the_post_call_for_vehicle_object_with_invalid_sub_model(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@When("I send a post request with the vehicle details with invalid vin")
    public void I_send_a_post_request_with_the_vehicle_details_with_invalid_vin(DataTable APIData){
    	List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		String vehicleendPoint = dataRow.get("id");
    	validatableResponse = 
        given()
		       .header("Authorization", "Bearer " + token)       
		       .contentType(ContentType.JSON)
               .body(object1.toString())
               .log().all()
        .when()
              .post(leadServiceEndPt +"/" +vehicleendPoint+"/vehicle")
              
        .then()
               .log().all();
    }
	
	@Then("I validate the response of the post call for vehicle object with invalid vin")
	public void I_validate_the_response_of_the_post_call_for_vehicle_object_with_invalid_vin(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
		
	@When("I send a post request with the vehicle details with invalid vehicle usage")
    public void I_send_a_post_request_with_the_vehicle_details_with_invalid_vehicle_usage(DataTable APIData){
    	List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		String vehicleendPoint = dataRow.get("id");
    	validatableResponse = 
        given()
		       .header("Authorization", "Bearer " + token)       
		       .contentType(ContentType.JSON)
               .body(object1.toString())
               .log().all()
        .when()
              .post(leadServiceEndPt +"/" +vehicleendPoint+"/vehicle")
              
        .then()
               .log().all();
    }
	
	@Then("I validate the response of the post call for vehicle object with invalid vehicle usage")
	public void I_validate_the_response_of_the_post_call_for_vehicle_object_with_invalid_vehicle_usage(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@When("I send a post request with the vehicle details with invalid Annual Mileage")
    public void I_send_a_post_request_with_the_vehicle_details_with_invalid_Annual_Mileage(DataTable APIData){
    	List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		String vehicleendPoint = dataRow.get("id");
    	validatableResponse = 
        given()
		       .header("Authorization", "Bearer " + token)       
		       .contentType(ContentType.JSON)
               .body(object1.toString())
               .log().all()
        .when()
              .post(leadServiceEndPt +"/" +vehicleendPoint+"/vehicle")
              
        .then()
               .log().all();
    }
	
	@Then("I validate the response of the post call for vehicle object with invalid Annual Mileage")
	public void I_validate_the_response_of_the_post_call_for_vehicle_object_with_invalid_Annual_Mileage(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}

    @Given("a valid grins lead id")
    public void a_valid_grins_lead_id(DataTable APIData){
    	List<Map<String, String>> leadId = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = leadId.get(0);
		leadServiceEndPt = leadServiceEndPt + "/v2?grins_lead_id=" + dataRow.get("id");
        System.out.println("Lead Service Endpoint :"+leadServiceEndPt);
    }
    
    @When("I send a get request to the lead service database")
    public void sendGETRequestleadservicedatabase(){
    	validatableResponse = 
	        given()
	        .header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
	        .when()
	        .get(leadServiceEndPt)
	        .then().log().all();
    	
    }
    
	@And("an invalid token for the lead service")
	public void an_invalid_token_for_the_lead_service() {
		token= "invalidTokencodevalue";
		System.out.println("Token: "+ token);
	}
	
    @When("I send a get request to the lead service database with invalid token")
    public void send_GET_Request_leadservice_database_with_invalid_token(){
    	validatableResponse = 
	        given()
	        .header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
	        .when()
	        .get(leadServiceEndPt)
	        .then().log().all();	
    }
    
    @Then("I validate the status response code with invalid token")
    public void verify_Status_Response_Code_invalid_Token(DataTable APIData){
    	List<Map<String, String>> status_code = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = status_code.get(0);
        validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
        validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
    }
	
}