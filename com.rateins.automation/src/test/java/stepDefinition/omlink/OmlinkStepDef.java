package stepDefinition.omlink;

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

public class OmlinkStepDef {
	public static WebDriver driver;
	public static Properties prop;

	protected ValidatableResponse validatableResponse;
	protected String omlinkEndPt = "https://link-qa.nonprod.rateins.com";
	protected String omlinktokenEndPt = "https://login.gr-dev.com/oauth2/aus1lsk5st100GteN1d7/v1/token";
	protected String client_id = System.getenv("AUTOMATION_LOGIN_GR_DEV_CLIENT_ID");
	protected String client_secret = System.getenv("AUTOMATION_LOGIN_GR_DEV_CLIENT_SECRET");
	protected String token;
	protected JSONObject object1 = new JSONObject();

	public OmlinkStepDef() {
		prop = TestInitialize.getProperties();
	}

	@Given("a omlink prefill endpoint URL")
	public void a_omlink_prefill_endpoint_URL(DataTable APIData) {
		List<Map<String, String>> leadId = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = leadId.get(0);
		omlinkEndPt = omlinkEndPt + "/prefill?loan_uuid=" + dataRow.get("loanID") + "&company="
				+ dataRow.get("Company");
		System.out.println("Quote Service Health check Endpoint :" + omlinkEndPt);

	}

	@And("a valid token in Omlink")
	public void a_valid_token_in_Omlink() {
		token = given().formParam("client_id", client_id).formParam("client_secret", client_secret)
				.formParam("grant_type", "client_credentials").log().all().when().post(omlinktokenEndPt).then().log()
				.all().extract().path("access_token");

		System.out.println("Token: " + token);
	}

	@When("I send a GET request for omlink prefill endpoint URL")
	public void I_send_a_GET_request_for_omlink_prefill_endpoint_URL() {
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).when()
				.get(omlinkEndPt).then().log().all();

	}

	@Then("validate the response of the omlink prefill endpoint with status code")
	public void validate_the_response_of_the_omlink_prefill_endpoint_with_status_code(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		validatableResponse.assertThat().body("eligible", equalTo(Boolean.parseBoolean(dataRow.get("eligible"))));
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));

	}

	/*
	 * @Then("validate the response of the omlink prefill endpoint with ineligible loan"
	 * ) public void
	 * validate_the_response_of_the_omlink_prefill_endpoint_with_ineligible_loan(
	 * DataTable APIData) { List<Map<String, String>> apiData =
	 * APIData.asMaps(String.class, String.class); Map<String, String> dataRow =
	 * apiData.get(0); validatableResponse.assertThat().body("success",
	 * equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	 * validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.
	 * get("statuscode"))));
	 * 
	 * }
	 */
	
	@And("an invalid token in Omlink")
	public void an_invalid_token_in_Omlink() {
		token= "invalidTokencode";
		System.out.println("Token: "+ token);
	}
	
	@Then("validate the response of the omlink prefill endpoint with invalid token")
	public void validate_the_response_of_the_omlink_prefill_endpoint_with_invalid_token(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	    validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));

	}
	
	@Then("validate the response of the omlink prefill endpoint with invalid company")
	public void validate_the_response_of_the_omlink_prefill_endpoint_with_invalid_company(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	    validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));

	}
	
	@Given("a omlink prefill endpoint URL with invalid endpoint")
	public void a_omlink_prefill_endpoint_URL_with_invalid_endpoint(DataTable APIData) {
		List<Map<String, String>> leadId = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = leadId.get(0);
		omlinkEndPt = omlinkEndPt + "/prefi?loan_uuid=" + dataRow.get("loanID") + "&company="
				+ dataRow.get("Company");
		System.out.println("Quote Service Health check Endpoint :" + omlinkEndPt);

	}
	
	@When("I send a GET request for omlink prefill endpoint URL with invalid endpoint")
	public void I_send_a_GET_request_for_omlink_prefill_endpoint_URL_with_invalid_endpoint() {
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).when()
				.get(omlinkEndPt).then().log().all();

	}

	@Then("validate the response of the omlink prefill endpoint with invalid endpoint")
	public void validate_the_response_of_the_omlink_prefill_endpoint_with_invalid_endpoint(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));

	}
}