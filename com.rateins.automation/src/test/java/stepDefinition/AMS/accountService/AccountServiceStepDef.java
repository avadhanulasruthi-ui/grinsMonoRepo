package stepDefinition.AMS.accountService;

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

public class AccountServiceStepDef {
	public static WebDriver driver;
	public static Properties prop;
	protected ValidatableResponse validatableResponse;
	protected String accountServiceEndPt = "https://api-qa.nonprod.rateins.com";
	protected String accountServicetokenEndPt = "https://gr.oktapreview.com/oauth2/ausvtetp64jEl2AuE0h7/v1/token";
	protected String client_id = System.getenv("AUTOMATION_LEAD_SERVICE_CLIENT_ID");
	protected String client_secret = System.getenv("AUTOMATION_LEAD_SERVICE_CLIENT_SECRET");
	protected String token;
	protected JSONObject object1 = new JSONObject();
	protected JSONObject object2 = new JSONObject();
    
	public AccountServiceStepDef()
	{
		prop = TestInitialize.getProperties();
	}
  	
    @Given("a valid account name")
    public void a_lead(DataTable APIData){
    	List<Map<String, String>> accountName = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = accountName.get(0);
		accountServiceEndPt = accountServiceEndPt + "/accounts?account_name=" + dataRow.get("account_name");
        System.out.println("AMS Accounts Service Endpoint :"+accountServiceEndPt);
    }
    
	@And("a valid bearer token")
	public void a_valid_bearer_token() {
		token= 
				given()
                .formParam("client_id", client_id)
                .formParam("client_secret", client_secret)
                .formParam("grant_type", "client_credentials").log().all()
                .when()
                .post(accountServicetokenEndPt)
                .then().log().all().extract().path("access_token");
      
		System.out.println("Token: "+ token);
	}
    
    @When("I send a GET request to fetch the Applied EPIC accounts")
    public void send_GET_Request_fetch_Applied_EPIC_accounts(){
    	validatableResponse = 
	        given()
	        .header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
	        .when()
	        .get(accountServiceEndPt)
	        .then().log().all();
    	
    }

    @Then("validate the response of the GET request for the Applied EPIC accounts")
    public void validate_the_response_of_the_GET_request_for_the_Applied_EPIC_accounts(DataTable APIData){
    	List<Map<String, String>> status_code = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = status_code.get(0);
        validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
    }
    
	@And("a invalid bearer token")
	public void a_invalid_bearer_token() {
		token= "invalidbearerTokencode";
		System.out.println("Token: "+ token);
	}
    
    @When("I send a GET request to fetch the Applied EPIC accounts with invalid token")
    public void send_GET_Request_fetch_Applied_EPIC_accounts_with_invalid_token(){
    	validatableResponse = 
	        given()
	        .header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
	        .when()
	        .get(accountServiceEndPt)
	        .then().log().all();
    	
    }

    @Then("validate the response of the GET request for the Applied EPIC accounts with invalid token")
    public void validate_the_response_of_the_GET_request_for_the_Applied_EPIC_accounts_with_invalid_token(DataTable APIData){
    	List<Map<String, String>> status_code = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = status_code.get(0);
        validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
        validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
    }
    
    @Given("the valid user details for EPIC account")
    public void the_valid_user_details_for_EPIC_account(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("account_name", dataRow.get("account_name"));
		object1.put("account_source", dataRow.get("account_source"));
		object1.put("city", dataRow.get("city"));
		object1.put("state", dataRow.get("state"));
		object1.put("street", dataRow.get("street"));
		object1.put("postal_code", dataRow.get("postal_code"));
		object1.put("employee_lookup_code", dataRow.get("employee_lookup_code"));
    	object2.put("first_name", dataRow.get("first_name"));
    	object2.put("last_name", dataRow.get("last_name"));
    	object2.put("phone", dataRow.get("phone"));
    	object1.put("primary_contact", object2);
		System.out.println(object1);
        System.out.println(object2);
    }
        
    @When("I send a POST request to create the Applied EPIC accounts")
    public void I_send_a_POST_request_to_create_the_Applied_EPIC_accounts(){
    	accountServiceEndPt = accountServiceEndPt + "/accounts";
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(accountServiceEndPt)
	        .then()
	               .assertThat().statusCode(201).log().all();
    }
    
	@Then("validate the response of the POST request for the Applied EPIC accounts creation")
	public void validate_the_response_of_the_POST_request_for_the_Applied_EPIC_accounts_creation(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}
    
	@Given("valid user details for EPIC account with invalid token")
    public void valid_user_details_for_EPIC_account_with_invalid_token(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("account_name", dataRow.get("account_name"));
		object1.put("state", dataRow.get("state"));
		object1.put("street", dataRow.get("street"));
		object1.put("city", dataRow.get("city"));
		object1.put("postal_code", dataRow.get("postal_code"));
		object1.put("account_source", dataRow.get("account_source"));
		object1.put("employee_lookup_code", dataRow.get("employee_lookup_code"));
    	object2.put("first_name", dataRow.get("first_name"));
    	object2.put("last_name", dataRow.get("last_name"));
    	object2.put("phone", dataRow.get("phone"));
    	object1.put("primary_contact", object2);
		System.out.println(object1);
        System.out.println(object2);
    }
        
    @When("I send a POST request to create the Applied EPIC accounts with invalid token")
    public void I_send_a_POST_request_to_create_the_Applied_EPIC_accounts_with_invalid_token(){
    	accountServiceEndPt = accountServiceEndPt + "/accounts";
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(accountServiceEndPt)
	        .then()
	               .assertThat().statusCode(401).log().all();
    }
    
	@Then("validate the response of the POST request for the Applied EPIC accounts creation with invalid token")
	public void validate_the_response_of_the_POST_request_for_the_Applied_EPIC_accounts_creation_with_invalid_token(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
    
	@Given("valid user details for EPIC account with invalid account name")
    public void valid_user_details_for_EPIC_account_with_invalid_account_name(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("state", dataRow.get("state"));
		object1.put("street", dataRow.get("street"));
		object1.put("city", dataRow.get("city"));
		object1.put("postal_code", dataRow.get("postal_code"));
		object1.put("account_source", dataRow.get("account_source"));
		object1.put("employee_lookup_code", dataRow.get("employee_lookup_code"));
    	object2.put("first_name", dataRow.get("first_name"));
    	object2.put("last_name", dataRow.get("last_name"));
    	object2.put("phone", dataRow.get("phone"));
    	object1.put("primary_contact", object2);
		System.out.println(object1);
        System.out.println(object2);
    }
        
    @When("I send a POST request to create the Applied EPIC accounts with invalid account name")
    public void I_send_a_POST_request_to_create_the_Applied_EPIC_accounts_with_invalid_account_name(){
    	accountServiceEndPt = accountServiceEndPt + "/accounts";
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(accountServiceEndPt)
	        .then()
	               .assertThat().statusCode(400).log().all();
    }
    
	@Then("validate the response of the POST request for the Applied EPIC accounts creation with invalid account name")
	public void validate_the_response_of_the_POST_request_for_the_Applied_EPIC_accounts_creation_with_invalid_account_name(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("the valid user details for EPIC activity details")
    public void the_valid_user_details_for_EPIC_activity_details(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("activity_code", dataRow.get("activity_code"));
		object1.put("state", dataRow.get("state"));
		object1.put("owner_code", dataRow.get("owner_code"));
		System.out.println(object1);
    }
        
    @When("I send a POST request to create the Applied EPIC activities")
    public void I_send_a_POST_request_to_create_the_Applied_EPIC_activities(){
    	accountServiceEndPt = accountServiceEndPt + "/accounts/119839/activity";
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(accountServiceEndPt)
	        .then()
	               .assertThat().statusCode(201).log().all();
    }
    
	@Then("validate the response of the POST request for the Applied EPIC activities creation")
	public void validate_the_response_of_the_POST_request_for_the_Applied_EPIC_activity_creation(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}
	
	@Given("the valid user details for EPIC activity details with invalid token")
    public void the_valid_user_details_for_EPIC_activity_details_with_invalid_token(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("activity_code", dataRow.get("activity_code"));
		object1.put("state", dataRow.get("state"));
		object1.put("owner_code", dataRow.get("owner_code"));
		System.out.println(object1);
    }
        
    @When("I send a POST request to create the Applied EPIC activities with invalid token")
    public void I_send_a_POST_request_to_create_the_Applied_EPIC_activities_with_invalid_token(){
    	accountServiceEndPt = accountServiceEndPt + "/accounts/119839/activity";
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(accountServiceEndPt)
	        .then()
	               .assertThat().statusCode(401).log().all();
    }
    
	@Then("validate the response of the POST request for the Applied EPIC activities creation with invalid token")
	public void validate_the_response_of_the_POST_request_for_the_Applied_EPIC_activity_creation_with_invalid_token(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	
	@Given("the valid user details for EPIC activity details with invalid activity code")
    public void the_valid_user_details_for_EPIC_activity_details_with_invalid_activity_code(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("activity_code", dataRow.get("activity_code"));
		object1.put("owner_code", dataRow.get("owner_code"));
		object1.put("state", dataRow.get("state"));
		System.out.println(object1);
    }
        
    @When("I send a POST request to create the Applied EPIC activities with invalid activity code")
    public void I_send_a_POST_request_to_create_the_Applied_EPIC_activities_with_invalid_activity_code(){
    	accountServiceEndPt = accountServiceEndPt + "/accounts/119839/activity";
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(accountServiceEndPt)
	        .then()
	               .assertThat().log().all();
    }
    
	@Then("validate the response of the POST request for the Applied EPIC activities creation with invalid activity code")
	public void validate_the_response_of_the_POST_request_for_the_Applied_EPIC_activity_creation_with_invalid_activity_code(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("the valid user details for EPIC activity details with lookup code")
    public void the_valid_user_details_for_EPIC_activity_details_with_lookup_code(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("activity_code", dataRow.get("activity_code"));
		object1.put("state", dataRow.get("state"));
		object1.put("owner_code", dataRow.get("owner_code"));
		System.out.println(object1);
    }
        
    @When("I send a POST request to create the Applied EPIC activities with lookup code")
    public void I_send_a_POST_request_to_create_the_Applied_EPIC_activities_with_lookup_code(){
    	accountServiceEndPt = accountServiceEndPt + "/accounts/lookup/WILKINAL01/activity";
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(accountServiceEndPt)
	        .then()
	               .assertThat().statusCode(201).log().all();
    }
    
	@Then("validate the response of the POST request for the Applied EPIC activities creation with lookup code")
	public void validate_the_response_of_the_POST_request_for_the_Applied_EPIC_activity_creation_with_lookup_code(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}
	
	@Given("the valid user details for EPIC activity details with lookup code and invalid token")
    public void the_valid_user_details_for_EPIC_activity_details_with_lookup_code_and_invalid_token(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("activity_code", dataRow.get("activity_code"));
		object1.put("state", dataRow.get("state"));
		object1.put("owner_code", dataRow.get("owner_code"));
		System.out.println(object1);
    }
        
    @When("I send a POST request to create the Applied EPIC activities with lookup code invalid token")
    public void I_send_a_POST_request_to_create_the_Applied_EPIC_activities_with_lookup_code_and_invalid_token(){
    	accountServiceEndPt = accountServiceEndPt + "/accounts/lookup/WILKINAL01/activity";
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(accountServiceEndPt)
	        .then()
	               .assertThat().log().all();
    }
    
	@Then("validate the response of the POST request for the Applied EPIC activities creation with lookup code and invalid token")
	public void validate_the_response_of_the_POST_request_for_the_Applied_EPIC_activity_creation_with_lookup_code_and_invalid_token(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("the valid user details for EPIC activity details with lookup code and no activity code")
    public void the_valid_user_details_for_EPIC_activity_details_with_lookup_code_and_no_activity_code(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("state", dataRow.get("state"));
		object1.put("owner_code", dataRow.get("owner_code"));
		System.out.println(object1);
    }
        
    @When("I send a POST request to create the Applied EPIC activities with lookup code and no activity code")
    public void I_send_a_POST_request_to_create_the_Applied_EPIC_activities_with_lookup_code_and_no_activity_code(){
    	accountServiceEndPt = accountServiceEndPt + "/accounts/lookup/WILKINAL01/activity";
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(accountServiceEndPt)
	        .then()
	               .assertThat().log().all();
    }
    
	@Then("validate the response of the POST request for the Applied EPIC activities creation with lookup code and no activity code")
	public void validate_the_response_of_the_POST_request_for_the_Applied_EPIC_activity_creation_with_lookup_code_and_no_activity_code(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("the valid user details for EPIC activity details with lookup code and invalid state")
    public void the_valid_user_details_for_EPIC_activity_details_with_lookup_code_and_invalid_state(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("activity_code", dataRow.get("activity_code"));
		object1.put("state", dataRow.get("state"));
		object1.put("owner_code", dataRow.get("owner_code"));
		System.out.println(object1);
    }
        
    @When("I send a POST request to create the Applied EPIC activities with lookup code and invalid state")
    public void I_send_a_POST_request_to_create_the_Applied_EPIC_activities_with_lookup_code_and_invalid_state(){
    	accountServiceEndPt = accountServiceEndPt + "/accounts/lookup/WILKINAL01/activity";
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(accountServiceEndPt)
	        .then()
	               .assertThat().log().all();
    }
    
	@Then("validate the response of the POST request for the Applied EPIC activities creation with lookup code and invalid state")
	public void validate_the_response_of_the_POST_request_for_the_Applied_EPIC_activity_creation_with_lookup_code_and_invalid_state(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("the valid user details for EPIC activity details with lookup code and no owner code")
    public void the_valid_user_details_for_EPIC_activity_details_with_lookup_code_and_no_owner_code(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("activity_code", dataRow.get("activity_code"));
		object1.put("state", dataRow.get("state"));
		System.out.println(object1);
    }
        
    @When("I send a POST request to create the Applied EPIC activities with lookup code and no owner code")
    public void I_send_a_POST_request_to_create_the_Applied_EPIC_activities_with_lookup_code_and_no_owner_code(){
    	accountServiceEndPt = accountServiceEndPt + "/accounts/lookup/WILKINAL01/activity";
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(accountServiceEndPt)
	        .then()
	               .assertThat().log().all();
    }
    
	@Then("validate the response of the POST request for the Applied EPIC activities creation with lookup code and no owner code")
	public void validate_the_response_of_the_POST_request_for_the_Applied_EPIC_activity_creation_with_lookup_code_and_no_owner_code(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("the valid user details for EPIC activity details with invalid lookup code")
    public void the_valid_user_details_for_EPIC_activity_details_with_lookup_code_and_invalid_lookupcode(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("activity_code", dataRow.get("activity_code"));
		object1.put("state", dataRow.get("state"));
		object1.put("owner_code", dataRow.get("owner_code"));
		System.out.println(object1);
    }
        
    @When("I send a POST request to create the Applied EPIC activities with invalid lookup code")
    public void I_send_a_POST_request_to_create_the_Applied_EPIC_activities_with_lookup_code_and_invalid_lookupcode(){
    	accountServiceEndPt = accountServiceEndPt + "/accounts/lookup/WILKIN01/activity";
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(accountServiceEndPt)
	        .then()
	               .assertThat().log().all();
    }
    
	@Then("validate the response of the POST request for the Applied EPIC activities creation with invalid lookup code")
	public void validate_the_response_of_the_POST_request_for_the_Applied_EPIC_activity_creation_with_lookup_code_and_invalid_lookupcode(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("the valid user details for EPIC activity details with invalid state")
    public void the_valid_user_details_for_EPIC_activity_details_with_invalid_state(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("activity_code", dataRow.get("activity_code"));
		object1.put("owner_code", dataRow.get("owner_code"));
		object1.put("state", dataRow.get("state"));
		System.out.println(object1);
    }
        
    @When("I send a POST request to create the Applied EPIC activities with invalid state")
    public void I_send_a_POST_request_to_create_the_Applied_EPIC_activities_with_invalid_state(){
    	accountServiceEndPt = accountServiceEndPt + "/accounts/119839/activity";
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(accountServiceEndPt)
	        .then()
	               .assertThat().log().all();
    }
    
	@Then("validate the response of the POST request for the Applied EPIC activities creation with invalid state")
	public void validate_the_response_of_the_POST_request_for_the_Applied_EPIC_activity_creation_with_invalid_state(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("the valid user details for EPIC activity details with no owner code")
    public void the_valid_user_details_for_EPIC_activity_details_with_no_owner_code(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("activity_code", dataRow.get("activity_code"));
		object1.put("state", dataRow.get("state"));
		System.out.println(object1);
    }
        
    @When("I send a POST request to create the Applied EPIC activities with no owner code")
    public void I_send_a_POST_request_to_create_the_Applied_EPIC_activities_with_no_owner_code(){
    	accountServiceEndPt = accountServiceEndPt + "/accounts/119839/activity";
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(accountServiceEndPt)
	        .then()
	               .assertThat().log().all();
    }
    
	@Then("validate the response of the POST request for the Applied EPIC activities creation with no owner code")
	public void validate_the_response_of_the_POST_request_for_the_Applied_EPIC_activity_creation_with_no_owner_code(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("valid user details for EPIC account with no account source")
    public void valid_user_details_for_EPIC_account_with_no_account_source(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("account_name", dataRow.get("account_name"));
		object1.put("state", dataRow.get("state"));
		object1.put("street", dataRow.get("street"));
		object1.put("city", dataRow.get("city"));
		object1.put("postal_code", dataRow.get("postal_code"));
		object1.put("employee_lookup_code", dataRow.get("employee_lookup_code"));
    	object2.put("first_name", dataRow.get("first_name"));
    	object2.put("last_name", dataRow.get("last_name"));
    	object2.put("phone", dataRow.get("phone"));
    	object1.put("primary_contact", object2);
		System.out.println(object1);
        System.out.println(object2);
    }
        
    @When("I send a POST request to create the Applied EPIC accounts with no account source")
    public void I_send_a_POST_request_to_create_the_Applied_EPIC_accounts_with_no_account_source(){
    	accountServiceEndPt = accountServiceEndPt + "/accounts";
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(accountServiceEndPt)
	        .then()
	               .assertThat().log().all();
    }
    
	@Then("validate the response of the POST request for the Applied EPIC accounts creation with no account source")
	public void validate_the_response_of_the_POST_request_for_the_Applied_EPIC_accounts_creation_with_no_account_source(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("valid user details for EPIC account with no city")
    public void valid_user_details_for_EPIC_account_with_no_city(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("account_name", dataRow.get("account_name"));
		object1.put("account_source", dataRow.get("account_source"));
		object1.put("state", dataRow.get("state"));
		object1.put("street", dataRow.get("street"));
		object1.put("postal_code", dataRow.get("postal_code"));
		object1.put("employee_lookup_code", dataRow.get("employee_lookup_code"));
    	object2.put("first_name", dataRow.get("first_name"));
    	object2.put("last_name", dataRow.get("last_name"));
    	object2.put("phone", dataRow.get("phone"));
    	object1.put("primary_contact", object2);
		System.out.println(object1);
        System.out.println(object2);
    }
        
    @When("I send a POST request to create the Applied EPIC accounts with no city")
    public void I_send_a_POST_request_to_create_the_Applied_EPIC_accounts_with_no_city(){
    	accountServiceEndPt = accountServiceEndPt + "/accounts";
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(accountServiceEndPt)
	        .then()
	               .assertThat().log().all();
    }
    
	@Then("validate the response of the POST request for the Applied EPIC accounts creation with no city")
	public void validate_the_response_of_the_POST_request_for_the_Applied_EPIC_accounts_creation_with_no_city(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("valid user details for EPIC account with no state")
    public void valid_user_details_for_EPIC_account_with_no_state(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("account_name", dataRow.get("account_name"));
		object1.put("account_source", dataRow.get("account_source"));
		object1.put("city", dataRow.get("city"));
		object1.put("street", dataRow.get("street"));
		object1.put("postal_code", dataRow.get("postal_code"));
		object1.put("employee_lookup_code", dataRow.get("employee_lookup_code"));
    	object2.put("first_name", dataRow.get("first_name"));
    	object2.put("last_name", dataRow.get("last_name"));
    	object2.put("phone", dataRow.get("phone"));
    	object1.put("primary_contact", object2);
		System.out.println(object1);
        System.out.println(object2);
    }
        
    @When("I send a POST request to create the Applied EPIC accounts with no state")
    public void I_send_a_POST_request_to_create_the_Applied_EPIC_accounts_with_no_state(){
    	accountServiceEndPt = accountServiceEndPt + "/accounts";
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(accountServiceEndPt)
	        .then()
	               .assertThat().log().all();
    }
    
	@Then("validate the response of the POST request for the Applied EPIC accounts creation with no state")
	public void validate_the_response_of_the_POST_request_for_the_Applied_EPIC_accounts_creation_with_no_state(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("valid user details for EPIC account with no street")
    public void valid_user_details_for_EPIC_account_with_no_street(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("account_name", dataRow.get("account_name"));
		object1.put("account_source", dataRow.get("account_source"));
		object1.put("city", dataRow.get("city"));
		object1.put("state", dataRow.get("state"));
		object1.put("postal_code", dataRow.get("postal_code"));
		object1.put("employee_lookup_code", dataRow.get("employee_lookup_code"));
    	object2.put("first_name", dataRow.get("first_name"));
    	object2.put("last_name", dataRow.get("last_name"));
    	object2.put("phone", dataRow.get("phone"));
    	object1.put("primary_contact", object2);
		System.out.println(object1);
        System.out.println(object2);
    }
        
    @When("I send a POST request to create the Applied EPIC accounts with no street")
    public void I_send_a_POST_request_to_create_the_Applied_EPIC_accounts_with_no_street(){
    	accountServiceEndPt = accountServiceEndPt + "/accounts";
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(accountServiceEndPt)
	        .then()
	               .assertThat().log().all();
    }
    
	@Then("validate the response of the POST request for the Applied EPIC accounts creation with no street")
	public void validate_the_response_of_the_POST_request_for_the_Applied_EPIC_accounts_creation_with_no_street(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("valid user details for EPIC account with no postal code")
    public void valid_user_details_for_EPIC_account_with_no_postal_code(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("account_name", dataRow.get("account_name"));
		object1.put("account_source", dataRow.get("account_source"));
		object1.put("city", dataRow.get("city"));
		object1.put("state", dataRow.get("state"));
		object1.put("street", dataRow.get("street"));
		object1.put("employee_lookup_code", dataRow.get("employee_lookup_code"));
    	object2.put("first_name", dataRow.get("first_name"));
    	object2.put("last_name", dataRow.get("last_name"));
    	object2.put("phone", dataRow.get("phone"));
    	object1.put("primary_contact", object2);
		System.out.println(object1);
        System.out.println(object2);
    }
        
    @When("I send a POST request to create the Applied EPIC accounts with no postal code")
    public void I_send_a_POST_request_to_create_the_Applied_EPIC_accounts_with_no_postal_code(){
    	accountServiceEndPt = accountServiceEndPt + "/accounts";
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(accountServiceEndPt)
	        .then()
	               .assertThat().log().all();
    }
    
	@Then("validate the response of the POST request for the Applied EPIC accounts creation with no postal code")
	public void validate_the_response_of_the_POST_request_for_the_Applied_EPIC_accounts_creation_with_no_postal_code(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("valid user details for EPIC account with no employee lookup code")
    public void valid_user_details_for_EPIC_account_with_no_employeelookup_code(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("account_name", dataRow.get("account_name"));
		object1.put("account_source", dataRow.get("account_source"));
		object1.put("city", dataRow.get("city"));
		object1.put("state", dataRow.get("state"));
		object1.put("street", dataRow.get("street"));
		object1.put("postal_code", dataRow.get("postal_code"));
    	object2.put("first_name", dataRow.get("first_name"));
    	object2.put("last_name", dataRow.get("last_name"));
    	object2.put("phone", dataRow.get("phone"));
    	object1.put("primary_contact", object2);
		System.out.println(object1);
        System.out.println(object2);
    }
        
    @When("I send a POST request to create the Applied EPIC accounts with no employee lookup code")
    public void I_send_a_POST_request_to_create_the_Applied_EPIC_accounts_with_no_employeelookup_code(){
    	accountServiceEndPt = accountServiceEndPt + "/accounts";
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(accountServiceEndPt)
	        .then()
	               .assertThat().log().all();
    }
    
	@Then("validate the response of the POST request for the Applied EPIC accounts creation with no employee lookup code")
	public void validate_the_response_of_the_POST_request_for_the_Applied_EPIC_accounts_creation_with_no_employeelookup_code(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("valid user details for EPIC account with no first name")
    public void valid_user_details_for_EPIC_account_with_no_first_name(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("account_name", dataRow.get("account_name"));
		object1.put("account_source", dataRow.get("account_source"));
		object1.put("city", dataRow.get("city"));
		object1.put("state", dataRow.get("state"));
		object1.put("street", dataRow.get("street"));
		object1.put("postal_code", dataRow.get("postal_code"));
    	object2.put("employee_lookup_code", dataRow.get("employee_lookup_code"));
    	object2.put("last_name", dataRow.get("last_name"));
    	object2.put("phone", dataRow.get("phone"));
    	object1.put("primary_contact", object2);
		System.out.println(object1);
        System.out.println(object2);
    }
        
    @When("I send a POST request to create the Applied EPIC accounts with no first name")
    public void I_send_a_POST_request_to_create_the_Applied_EPIC_accounts_with_no_first_name(){
    	accountServiceEndPt = accountServiceEndPt + "/accounts";
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(accountServiceEndPt)
	        .then()
	               .assertThat().log().all();
    }
    
	@Then("validate the response of the POST request for the Applied EPIC accounts creation with no first name")
	public void validate_the_response_of_the_POST_request_for_the_Applied_EPIC_accounts_creation_with_no_first_name(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("valid user details for EPIC account with no last name")
    public void valid_user_details_for_EPIC_account_with_no_last_name(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("account_name", dataRow.get("account_name"));
		object1.put("account_source", dataRow.get("account_source"));
		object1.put("city", dataRow.get("city"));
		object1.put("state", dataRow.get("state"));
		object1.put("street", dataRow.get("street"));
		object1.put("postal_code", dataRow.get("postal_code"));
    	object2.put("employee_lookup_code", dataRow.get("employee_lookup_code"));
    	object2.put("first_name", dataRow.get("first_name"));
    	object2.put("phone", dataRow.get("phone"));
    	object1.put("primary_contact", object2);
		System.out.println(object1);
        System.out.println(object2);
    }
        
    @When("I send a POST request to create the Applied EPIC accounts with no last name")
    public void I_send_a_POST_request_to_create_the_Applied_EPIC_accounts_with_no_last_name(){
    	accountServiceEndPt = accountServiceEndPt + "/accounts";
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(accountServiceEndPt)
	        .then()
	               .assertThat().log().all();
    }
    
	@Then("validate the response of the POST request for the Applied EPIC accounts creation with no last name")
	public void validate_the_response_of_the_POST_request_for_the_Applied_EPIC_accounts_creation_with_no_last_name(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("valid user details for EPIC account with no phone number")
    public void valid_user_details_for_EPIC_account_with_no_phone_number(DataTable userData){
    	List<Map<String, String>> data = userData.asMaps(String.class, String.class);
		Map<String, String> dataRow = data.get(0);
		object1.put("account_name", dataRow.get("account_name"));
		object1.put("account_source", dataRow.get("account_source"));
		object1.put("city", dataRow.get("city"));
		object1.put("state", dataRow.get("state"));
		object1.put("street", dataRow.get("street"));
		object1.put("postal_code", dataRow.get("postal_code"));
    	object2.put("employee_lookup_code", dataRow.get("employee_lookup_code"));
    	object2.put("last_name", dataRow.get("last_name"));
    	object2.put("first_name", dataRow.get("first_name"));
    	object1.put("primary_contact", object2);
		System.out.println(object1);
        System.out.println(object2);
    }
        
    @When("I send a POST request to create the Applied EPIC accounts with no phone number")
    public void I_send_a_POST_request_to_create_the_Applied_EPIC_accounts_with_no_phone_number(){
    	accountServiceEndPt = accountServiceEndPt + "/accounts";
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(object1.toString())
	               .log().all()
	        .when()
	              .post(accountServiceEndPt)
	        .then()
	               .assertThat().log().all();
    }
    
	@Then("validate the response of the POST request for the Applied EPIC accounts creation with no phone number")
	public void validate_the_response_of_the_POST_request_for_the_Applied_EPIC_accounts_creation_with_no_phone_number(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("no account name")
    public void no_account_name(){
		accountServiceEndPt = accountServiceEndPt + "/accounts?account_name=";
        System.out.println("AMS Accounts Service Endpoint :"+accountServiceEndPt);
    }
        
    @When("I send a GET request to fetch the Applied EPIC accounts with no account name")
    public void send_GET_Request_fetch_Applied_EPIC_accounts_with_no_account_name(){
    	validatableResponse = 
	        given()
	        .header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
	        .when()
	        .get(accountServiceEndPt)
	        .then().log().all();
    	
    }

    @Then("validate the response of the GET request for the Applied EPIC accounts with no account name")
    public void validate_the_response_of_the_GET_request_for_the_Applied_EPIC_accounts_with_no_account_name(DataTable APIData){
    	List<Map<String, String>> status_code = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = status_code.get(0);
        validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
        validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
    }
	
    @Given("an invalid account name")
    public void invalid_account_name(DataTable APIData){
    	List<Map<String, String>> accountName = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = accountName.get(0);
		accountServiceEndPt = accountServiceEndPt + "/accounts?account_name=" + dataRow.get("account_name");
        System.out.println("AMS Accounts Service Endpoint :"+accountServiceEndPt);
    }
    
    @When("I send a GET request to fetch the Applied EPIC accounts with an invalid account name")
    public void send_GET_Request_fetch_Applied_EPIC_accounts_with_invalid_account_name(){
    	validatableResponse = 
	        given()
	        .header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
	        .when()
	        .get(accountServiceEndPt)
	        .then().log().all();
    	
    }

    @Then("validate the response of the GET request for the Applied EPIC accounts with an invalid account name")
    public void validate_the_response_of_the_GET_request_for_the_Applied_EPIC_accounts_with_invalid_account_name(DataTable APIData){
    	List<Map<String, String>> status_code = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = status_code.get(0);
        validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
    }
	
}