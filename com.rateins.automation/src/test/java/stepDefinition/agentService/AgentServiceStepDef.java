package stepDefinition.agentService;

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

public class AgentServiceStepDef {
	public static WebDriver driver;
	public static Properties prop;
		
	protected ValidatableResponse validatableResponse;
	protected String agentServiceEndPt = "https://qa.nonprod.rateins.com/agents";
	protected String agentServicetokenEndPt = "https://gr.oktapreview.com/oauth2/ausvtetp64jEl2AuE0h7/v1/token";
	protected String client_id = System.getenv("AUTOMATION_LEAD_SERVICE_CLIENT_ID");
	protected String client_secret = System.getenv("AUTOMATION_LEAD_SERVICE_CLIENT_SECRET");
	protected String token;
	protected JSONObject object1 = new JSONObject();
	protected JSONObject object2 = new JSONObject();
    
	public AgentServiceStepDef()
	{
		prop = TestInitialize.getProperties();
	}
  	
    @Given("a valid agent details")
    public void a_valid_agent(DataTable APIData){
    	List<Map<String, String>> agentdetails = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = agentdetails.get(0);
		agentServiceEndPt = agentServiceEndPt + "?firstName=" + dataRow.get("firstName")+ "&lastName=" + dataRow.get("lastName");
        System.out.println("Agent Service Endpoint :" + agentServiceEndPt);
    }
    
	@And("a valid authorization token")
	public void auth_token() {
		token= 
				given()
                .formParam("client_id", client_id)
                .formParam("client_secret", client_secret)
                .formParam("grant_type", "client_credentials").log().all()
                .when()
                .post(agentServicetokenEndPt)
                .then().log().all().extract().path("access_token");
      
		System.out.println("Valid Token: "+ token);
	}
    
	@When("I send a get request with the agent details")
    public void I_send_a_get_request_with_the_agent_details(){
    	validatableResponse = 
        given()
		       .header("Authorization", "Bearer " + token)       
		       .contentType(ContentType.JSON)
               .body(object1.toString())
               .log().all()
        .when()
              .get(agentServiceEndPt)
              
        .then()
               .log().all();
    }
	
	@Then("I validate the response of the get call")
	public void I_validate_the_response_of_the_get_call(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().body("name",equalTo(dataRow.get("name")));
		validatableResponse.assertThat().body("sfUserId",equalTo(dataRow.get("sfUserId")));
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}

	@And("an invalid authorization token")
	public void an_invalid_authorization_token() {
		token= "invalidTokencode";
		System.out.println("Token: "+ token);
	}
	
	@When("I send a get request with the agent details with invalid token")
    public void I_send_a_get_request_with_the_agent_details_with_invalid_token(){
    	validatableResponse = 
        given()
		       .header("Authorization", "Bearer " + token)       
		       .contentType(ContentType.JSON)
               .body(object1.toString())
               .log().all()
        .when()
              .get(agentServiceEndPt)
              
        .then()
               .log().all();
    }
	
	@Then("I validate the response of the get call with invalid token")
	public void I_validate_the_response_of_the_get_call_with_invalid_token(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
    @Given("a invalid agent details with no last name")
    public void a_invalid_agent_no_last_name(DataTable APIData){
    	List<Map<String, String>> agentdetails = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = agentdetails.get(0);
		agentServiceEndPt = agentServiceEndPt + "?firstName=" + dataRow.get("firstName")+ "&lastName=";
        System.out.println("Agent Service Endpoint :" + agentServiceEndPt);
    }
	
	@When("I send a get request with the agent details with no last name")
    public void I_send_a_get_request_with_the_agent_details_with_no_last_name(){
    	validatableResponse = 
        given()
		       .header("Authorization", "Bearer " + token)       
		       .contentType(ContentType.JSON)
               .body(object1.toString())
               .log().all()
        .when()
              .get(agentServiceEndPt)
              
        .then()
               .log().all();
    }
	
	@Then("I validate the response of the get call with no last name")
	public void I_validate_the_response_of_the_get_call_with_no_last_name(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}
	
    @Given("a invalid agent details with no first name")
    public void a_invalid_agent_no_first_name(DataTable APIData){
    	List<Map<String, String>> agentdetails = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = agentdetails.get(0);
		agentServiceEndPt = agentServiceEndPt + "?firstName=&lastName=" + dataRow.get("lastName");
        System.out.println("Agent Service Endpoint :" + agentServiceEndPt);
    }
	
	@When("I send a get request with the agent details with no first name")
    public void I_send_a_get_request_with_the_agent_details_with_no_first_name(){
    	validatableResponse = 
        given()
		       .header("Authorization", "Bearer " + token)       
		       .contentType(ContentType.JSON)
               .body(object1.toString())
               .log().all()
        .when()
              .get(agentServiceEndPt)
              
        .then()
               .log().all();
    }
	
	@Then("I validate the response of the get call with no first name")
	public void I_validate_the_response_of_the_get_call_with_no_first_name(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}

    @Given("a invalid agent details with invalid first name")
    public void a_invalid_agent_invalid_first_name(DataTable APIData){
    	List<Map<String, String>> agentdetails = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = agentdetails.get(0);
		agentServiceEndPt = agentServiceEndPt + "?firstName=" + dataRow.get("firstName")+ "&lastName=" + dataRow.get("lastName");
        System.out.println("Agent Service Endpoint :" + agentServiceEndPt);
    }
	
	@When("I send a get request with the agent details with invalid first name")
    public void I_send_a_get_request_with_the_agent_details_with_invalid_first_name(){
    	validatableResponse = 
        given()
		       .header("Authorization", "Bearer " + token)       
		       .contentType(ContentType.JSON)
               .body(object1.toString())
               .log().all()
        .when()
              .get(agentServiceEndPt)
              
        .then()
               .log().all();
    }
	
	@Then("I validate the response of the get call with invalid first name")
	public void I_validate_the_response_of_the_get_call_with_invalid_first_name(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("a invalid agent details with invalid last name")
    public void a_invalid_agent_invalid_last_name(DataTable APIData){
    	List<Map<String, String>> agentdetails = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = agentdetails.get(0);
		agentServiceEndPt = agentServiceEndPt + "?firstName=" + dataRow.get("firstName")+ "&lastName=" + dataRow.get("lastName");
        System.out.println("Agent Service Endpoint :" + agentServiceEndPt);
    }
	
	@When("I send a get request with the agent details with invalid last name")
    public void I_send_a_get_request_with_the_agent_details_with_invalid_last_name(){
    	validatableResponse = 
        given()
		       .header("Authorization", "Bearer " + token)       
		       .contentType(ContentType.JSON)
               .body(object1.toString())
               .log().all()
        .when()
              .get(agentServiceEndPt)
              
        .then()
               .log().all();
    }
	
	@Then("I validate the response of the get call with invalid last name")
	public void I_validate_the_response_of_the_get_call_with_invalid_last_name(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("a invalid agent details with invalid first and last name")
    public void a_invalid_agent_invalid_first_and_last_name(DataTable APIData){
    	List<Map<String, String>> agentdetails = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = agentdetails.get(0);
		agentServiceEndPt = agentServiceEndPt + "?firstName=" + dataRow.get("firstName")+ "&lastName=" + dataRow.get("lastName");
        System.out.println("Agent Service Endpoint :" + agentServiceEndPt);
    }
	
	@When("I send a get request with the agent details with invalid first and last name")
    public void I_send_a_get_request_with_the_agent_details_with_invalid_first_and_last_name(){
    	validatableResponse = 
        given()
		       .header("Authorization", "Bearer " + token)       
		       .contentType(ContentType.JSON)
               .body(object1.toString())
               .log().all()
        .when()
              .get(agentServiceEndPt)
              
        .then()
               .log().all();
    }
	
	@Then("I validate the response of the get call with invalid first and last name")
	public void I_validate_the_response_of_the_get_call_with_invalid_first_and_last_name(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("a valid end point url")
    public void a_valid_agent_endpoint_url(){
		agentServiceEndPt = agentServiceEndPt + "/names";
        System.out.println("Agent Service Endpoint :" + agentServiceEndPt);
    }
	
	@When("I send a get request to fetch all the agent details")
    public void I_send_a_get_request_to_fetch_all_the_agent_details(){
    	validatableResponse = 
        given()
		       .header("x-api-key", "4b48c3e5-c6d6-4dbe-b6d3-23b8561ede37")       
		       .contentType(ContentType.JSON)
               .log().all()
        .when()
              .get(agentServiceEndPt)
              
        .then()
               .log().all();
    }
    
	@Then("I validate the response of the get call whether all user details are fetched")
	public void I_validate_the_response_of_the_get_call_to_fetch_all_user_details(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}
	
	@When("I send a get request to fetch all the agent details with invalid key")
    public void I_send_a_get_request_to_fetch_all_the_agent_details_with_invalid_key(){
    	validatableResponse = 
        given()
		       .header("x-api-key", "4b48c3e5-c6d6-4dbe-b6d3-23b8561ede")       
		       .contentType(ContentType.JSON)
               .log().all()
        .when()
              .get(agentServiceEndPt)
              
        .then()
               .log().all();
    }
     
	@Then("I validate the response of the get call whether all user details are fetched with invalid key")
	public void I_validate_the_response_of_the_get_call_to_fetch_all_user_details_with_invalid_key(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}
	
	@Given("a invalid end point url")
    public void a_invalid_agent_endpoint_url(){
		agentServiceEndPt = agentServiceEndPt + "/name";
        System.out.println("Agent Service Endpoint :" + agentServiceEndPt);
    }
	
	@When("I send a get request to fetch all the agent details with valid key")
    public void I_send_a_get_request_to_fetch_all_the_agent_details_with_valid_key_invalid_endpoint(){
    	validatableResponse = 
        given()
		       .header("x-api-key", "4b48c3e5-c6d6-4dbe-b6d3-23b8561ede37")       
		       .contentType(ContentType.JSON)
               .log().all()
        .when()
              .get(agentServiceEndPt)
              
        .then()
               .log().all();
    }
	
	@Then("I validate the response of the get call whether all user details are fetched with invalid endpoint")
	public void I_validate_the_response_of_the_get_call_to_fetch_all_user_details_with_invalid_endpoint(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body"+ validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}
	
}