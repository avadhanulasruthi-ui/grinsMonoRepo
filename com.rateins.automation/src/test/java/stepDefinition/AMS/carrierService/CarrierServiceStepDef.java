package stepDefinition.AMS.carrierService;

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

public class CarrierServiceStepDef {
	public static WebDriver driver;
	public static Properties prop;
	protected ValidatableResponse validatableResponse;
	protected String carrierServiceEndPt = "https://api-qa.nonprod.rateins.com";
	protected String carrierServicetokenEndPt = "https://gr.oktapreview.com/oauth2/ausvtetp64jEl2AuE0h7/v1/token";
	protected String client_id = System.getenv("AUTOMATION_LEAD_SERVICE_CLIENT_ID");
	protected String client_secret = System.getenv("AUTOMATION_LEAD_SERVICE_CLIENT_SECRET");
	protected String token;
	protected JSONObject object1 = new JSONObject();

	public CarrierServiceStepDef() {
		prop = TestInitialize.getProperties();
	}

	@Given("a AMS carrier service endpoint URL")
	public void a_AMS_carrier_service_endpoint_URL() {
		carrierServiceEndPt = carrierServiceEndPt + "/carriers";
		System.out.println("AMS Carrier Service Endpoint :" + carrierServiceEndPt);

	}

	@And("a valid token in AMS carrier service")
	public void a_valid_token_in_AMS_carrier_service() {
		token = given().formParam("client_id", client_id).formParam("client_secret", client_secret)
				.formParam("grant_type", "client_credentials").log().all().when().post(carrierServicetokenEndPt).then()
				.log().all().extract().path("access_token");

		System.out.println("Token: " + token);
	}

	@When("I send a GET request for AMS carrier service endpoint URL")
	public void I_send_a_GET_request_for_AMS_carrier_service_endpoint_URL() {
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).when()
				.get(carrierServiceEndPt).then().log().all();

	}

	@Then("validate the response of the AMS carrier service endpoint")
	public void validate_the_response_of_the_AMS_carrier_service_endpoint(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));

	}
	
	@And("an invalid token in AMS carrier service")
	public void an_invalid_token_in_AMS_carrier_service() {
		token= "invalidTokencode";
		System.out.println("Token: "+ token);
	}
	
	@Given("a AMS carrier service endpoint URL for invalid token")
	public void a_AMS_carrier_service_endpoint_URL_for_invalid_token() {
		carrierServiceEndPt = carrierServiceEndPt + "/carriers";
		System.out.println("AMS Carrier Service Endpoint :" + carrierServiceEndPt);

	}

	@When("I send a GET request for AMS carrier service endpoint URL for invalid token")
	public void I_send_a_GET_request_for_AMS_carrier_service_endpoint_URL_for_invalid_token() {
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).when()
				.get(carrierServiceEndPt).then().log().all();

	}

	@Then("validate the response of the AMS carrier service endpoint for invalid token")
	public void validate_the_response_of_the_AMS_carrier_service_endpoint_for_invalid_token(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("a AMS carrier service endpoint URL for invalid endpoint")
	public void a_AMS_carrier_service_endpoint_URL_for_invalid_endpoint() {
		carrierServiceEndPt = carrierServiceEndPt + "/carrie";
		System.out.println("AMS Carrier Service Endpoint :" + carrierServiceEndPt);

	}

	@When("I send a GET request for AMS carrier service endpoint URL for invalid endpoint")
	public void I_send_a_GET_request_for_AMS_carrier_service_endpoint_URL_for_invalid_endpoint() {
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).when()
				.get(carrierServiceEndPt).then().log().all();

	}

	@Then("validate the response of the AMS carrier service endpoint for invalid endpoint")
	public void validate_the_response_of_the_AMS_carrier_service_endpoint_for_invalid_endpoint(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}
	
}