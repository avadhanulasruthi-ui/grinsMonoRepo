package stepDefinition.quoteService;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
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
import io.restassured.specification.RequestSenderOptions;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class QuotesStartAPIStepDef {
	public static WebDriver driver;
	public static Properties prop;

	protected ValidatableResponse validatableResponse;
	protected String token;
	protected String obj;
	protected String obj1;

	public QuotesStartAPIStepDef() {
		prop = TestInitialize.getProperties();
	}

	protected String quoteServiceEndPt = "https://api-qa.nonprod.rateins.com";
	protected String quoteServicetokenEndPt = "https://gr.oktapreview.com/oauth2/ausvtetp64jEl2AuE0h7/v1/token";
	protected String client_id1 = System.getenv("AUTOMATION_LEAD_SERVICE_CLIENT_ID");
	protected String client_secret1 = System.getenv("AUTOMATION_LEAD_SERVICE_CLIENT_SECRET");
	
	@Given("a Quote start API end point URL")
	public void a_Quote_start_API_end_point_URL() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteStartAPI/quoteStartAPI_happyPath.json"));
		obj = new String(b);
		System.out.println(obj);
	}

	@And("a valid token for quotes start API")
	public void a_valid_token_for_quotes_start_API() {
		token = given().formParam("client_id", client_id1).formParam("client_secret", client_secret1)
				.formParam("grant_type", "client_credentials").log().all().when().post(quoteServicetokenEndPt).then()
				.log().all().extract().path("access_token");
		System.out.println("Token: " + token);
	}

	@When("I send a post request with valid loan ID valid agent ID and valid lead ID")
	public void I_send_a_post_request_with_valid_loan_ID_valid_agent_ID_and_valid_lead_ID() {
		System.out.println("Obj in this method is:" + obj);
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/start";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(obj)
				.log().all().when().post(quoteServiceEndPt).then().assertThat().statusCode(200).log().all();
	}

	@Then("I validate the response of the post call for valid loan ID valid agent ID and valid lead ID")
	public void I_validate_the_response_of_the_post_call_for_valid_loan_ID_valid_agent_ID_and_valid_lead_ID(
			DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}

	@And("an invalid token for quotes start API")
	public void an_invalid_token_id_in_HO3_flow() {
		token= "invalidTokencode";
		System.out.println("Token: "+ token);
	}
	
	@When("I send a post request in Quote start API with invalid token")
	public void I_send_a_post_request_in_Quote_start_API_with_invalid_token() {
		System.out.println("Obj in this method is:" + obj);
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/start";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(obj)
				.log().all().when().post(quoteServiceEndPt).then().assertThat().statusCode(401).log().all();
	}

	@Then("I validate the response of the post call in Quote start API with invalid token")
	public void I_validate_the_response_of_the_post_call_in_Quote_start_API_with_invalid_token(
			DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}
	
	@Given("a Quote start API end point URL with invalid loan ID")
	public void a_Quote_start_API_end_point_URL_with_invalid_loan_ID() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteStartAPI/quoteStartAPI_invalidLoanID.json"));
		obj = new String(b);
		System.out.println(obj);
	}
	
	@When("I send a post request in Quote start API with invalid loan ID")
	public void I_send_a_post_request_in_Quote_start_API_with_invalid_loan_ID() {
		System.out.println("Obj in this method is:" + obj);
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/start";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(obj)
				.log().all().when().post(quoteServiceEndPt).then().assertThat().statusCode(400).log().all();
	}

	@Then("I validate the response of the post call in Quote start API with invalid loan ID")
	public void I_validate_the_response_of_the_post_call_in_Quote_start_API_with_invalid_loan_ID(
			DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}
	
	@Given("a Quote start API end point URL with invalid agent ID")
	public void a_Quote_start_API_end_point_URL_with_invalid_agent_ID() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteStartAPI/quoteStartAPI_invalidAgentID.json"));
		obj = new String(b);
		System.out.println(obj);
	}
	
	@When("I send a post request in Quote start API with invalid agent ID")
	public void I_send_a_post_request_in_Quote_start_API_with_invalid_agent_ID() {
		System.out.println("Obj in this method is:" + obj);
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/start";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(obj)
				.log().all().when().post(quoteServiceEndPt).then().assertThat().statusCode(400).log().all();
	}

	@Then("I validate the response of the post call in Quote start API with invalid agent ID")
	public void I_validate_the_response_of_the_post_call_in_Quote_start_API_with_invalid_agent_ID(
			DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}
	
	@Given("a Quote start API end point URL with invalid lead ID")
	public void a_Quote_start_API_end_point_URL_with_invalid_lead_ID() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteStartAPI/quoteStartAPI_invalidLeadID.json"));
		obj = new String(b);
		System.out.println(obj);
	}
	
	@When("I send a post request in Quote start API with invalid lead ID")
	public void I_send_a_post_request_in_Quote_start_API_with_invalid_lead_ID() {
		System.out.println("Obj in this method is:" + obj);
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/start";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(obj)
				.log().all().when().post(quoteServiceEndPt).then().assertThat().statusCode(400).log().all();
	}

	@Then("I validate the response of the post call in Quote start API with invalid lead ID")
	public void I_validate_the_response_of_the_post_call_in_Quote_start_API_with_invalid_lead_ID(
			DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}
}