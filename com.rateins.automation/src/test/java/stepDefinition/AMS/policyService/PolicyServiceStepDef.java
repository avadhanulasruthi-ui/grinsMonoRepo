package stepDefinition.AMS.policyService;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

public class PolicyServiceStepDef {
	public static WebDriver driver;
	public static Properties prop;
	protected ValidatableResponse validatableResponse;
	protected String policyServiceEndPt = "https://api-qa.nonprod.rateins.com";
	protected String policyServicetokenEndPt = "https://gr.oktapreview.com/oauth2/ausvtetp64jEl2AuE0h7/v1/token";
	protected String client_id = System.getenv("AUTOMATION_LEAD_SERVICE_CLIENT_ID");
	protected String client_secret = System.getenv("AUTOMATION_LEAD_SERVICE_CLIENT_SECRET");
	protected String token;
	protected String obj1;
	

	public PolicyServiceStepDef() {
		prop = TestInitialize.getProperties();
	}

	@Given("a AMS policy service endpoint URL")
	public void a_AMS_policy_service_endpoint_URL() {
		policyServiceEndPt = policyServiceEndPt + "/policies/healthcheck";
		System.out.println("AMS Policy Service Endpoint :" + policyServiceEndPt);

	}

	@When("I send a GET request for AMS policy service endpoint URL")
	public void I_send_a_GET_request_for_AMS_policy_service_endpoint_URL() {
		validatableResponse = given().contentType(ContentType.JSON).when()
				.get(policyServiceEndPt).then().log().all();

	}

	@Then("validate the response of the AMS policy service endpoint")
	public void validate_the_response_of_the_AMS_policy_service_endpoint(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}
	
	@Given("a AMS policy service endpoint URL for invalid endpoint")
	public void a_AMS_policy_service_endpoint_URL_for_invalid_endpoint() {
		policyServiceEndPt = policyServiceEndPt + "/polici";
		System.out.println("AMS Policy Service Endpoint :" + policyServiceEndPt);

	}

	@When("I send a GET request for AMS policy service endpoint URL for invalid endpoint")
	public void I_send_a_GET_request_for_AMS_policy_service_endpoint_URL_for_invalid_endpoint() {
		validatableResponse = given().contentType(ContentType.JSON).when()
				.get(policyServiceEndPt).then().log().all();

	}

	@Then("validate the response of the AMS policy service endpoint for invalid endpoint")
	public void validate_the_response_of_the_AMS_policy_service_endpoint_for_invalid_endpoint(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}
	
	@Given("valid data and valid client id in AMS policy service")
	public void valid_data_and_valid_client_id_in_AMS_policy_service() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/AMS/policyService/amsPolicyService_validData_validClientID.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@And("a valid token in AMS policy service flow")
	public void a_valid_token_in_AMS_policy_service_flow() {
		token = given().formParam("client_id", client_id).formParam("client_secret", client_secret)
				.formParam("grant_type", "client_credentials").log().all().when().post(policyServicetokenEndPt).then()
				.log().all().extract().path("access_token");

		System.out.println("Token: " + token);
	}
	
	@When("I send a post request with valid data and valid client id in AMS policy service")
	public void I_send_a_post_request_with_valid_data_and_valid_client_id_in_AMS_policy_service() {
		policyServiceEndPt = policyServiceEndPt + "/policies";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(obj1).log().all().when()
				.post(policyServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for valid data and valid client id in AMS policy service")
	public void I_validate_the_response_of_the_post_call_for_valid_data_and_valid_client_id_in_AMS_policy_service(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("valid data and valid lookup code in AMS policy service")
	public void valid_data_and_valid_lookup_code_in_AMS_policy_service() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/AMS/policyService/amsPolicyService_validData_validLookupCode.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with valid data and valid lookup code in AMS policy service")
	public void I_send_a_post_request_with_valid_data_and_valid_lookup_code_in_AMS_policy_service() {
		policyServiceEndPt = policyServiceEndPt + "/policies";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(obj1).log().all().when()
				.post(policyServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for valid data and valid lookup code in AMS policy service")
	public void I_validate_the_response_of_the_post_call_for_valid_data_and_valid_lookup_code_in_AMS_policy_service(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@And("an invalid token in AMS policy service")
	public void an_invalid_token_in_AMS_policy_service() {
		token= "invalidTokencode";
		System.out.println("Token: "+ token);
	}
	
	@When("I send a post request with valid data and invalid token in AMS policy service")
	public void I_send_a_post_request_with_valid_data_and_invalid_token_in_AMS_policy_service() {
		policyServiceEndPt = policyServiceEndPt + "/policies";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(obj1).log().all().when()
				.post(policyServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for valid data and invalid token in AMS policy service")
	public void I_validate_the_response_of_the_post_call_for_valid_data_and_invalid_token_in_AMS_policy_service(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("invalid client id in AMS policy service")
	public void valid_data_and_invalid_client_id_in_AMS_policy_service() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/AMS/policyService/amsPolicyService_invalidClientID.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with invalid client id in AMS policy service")
	public void I_send_a_post_request_with_invalid_client_id_in_AMS_policy_service() {
		policyServiceEndPt = policyServiceEndPt + "/policies";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(obj1).log().all().when()
				.post(policyServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for invalid client id in AMS policy service")
	public void I_validate_the_response_of_the_post_call_for_invalid_client_id_in_AMS_policy_service(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("invalid lookup code in AMS policy service")
	public void valid_data_and_invalid_lookup_code_in_AMS_policy_service() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/AMS/policyService/amsPolicyService_invalidLookupCode.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with invalid lookup code in AMS policy service")
	public void I_send_a_post_request_with_invalid_lookup_code_in_AMS_policy_service() {
		policyServiceEndPt = policyServiceEndPt + "/policies";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(obj1).log().all().when()
				.post(policyServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for invalid lookup code in AMS policy service")
	public void I_validate_the_response_of_the_post_call_for_invalid_lookup_code_in_AMS_policy_service(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("invalid agreement id in AMS policy service")
	public void valid_data_and_invalid_agreement_id_in_AMS_policy_service() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/AMS/policyService/amsPolicyService_invalidAgreementId.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with invalid agreement id in AMS policy service")
	public void I_send_a_post_request_with_invalid_agreement_id_in_AMS_policy_service() {
		policyServiceEndPt = policyServiceEndPt + "/policies";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(obj1).log().all().when()
				.post(policyServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for invalid agreement id in AMS policy service")
	public void I_validate_the_response_of_the_post_call_for_invalid_agreement_id_in_AMS_policy_service(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("invalid policy description in AMS policy service")
	public void invalid_policy_description_in_AMS_policy_service() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/AMS/policyService/amsPolicyService_invalidPolicyDescription.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with invalid policy description in AMS policy service")
	public void I_send_a_post_request_with_invalid_policy_description_in_AMS_policy_service() {
		policyServiceEndPt = policyServiceEndPt + "/policies";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(obj1).log().all().when()
				.post(policyServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for invalid policy description in AMS policy service")
	public void I_validate_the_response_of_the_post_call_for_invalid_policy_description_in_AMS_policy_service(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("invalid effective date in AMS policy service")
	public void invalid_effective_date_in_AMS_policy_service() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/AMS/policyService/amsPolicyService_invalidEffectiveDate.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with invalid effective date in AMS policy service")
	public void I_send_a_post_request_with_invalid_effective_date_in_AMS_policy_service() {
		policyServiceEndPt = policyServiceEndPt + "/policies";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(obj1).log().all().when()
				.post(policyServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for invalid effective date in AMS policy service")
	public void I_validate_the_response_of_the_post_call_for_invalid_effective_date_in_AMS_policy_service(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("invalid expiration date in AMS policy service")
	public void invalid_expiration_date_in_AMS_policy_service() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/AMS/policyService/amsPolicyService_invalidExpirationDate.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with invalid expiration date in AMS policy service")
	public void I_send_a_post_request_with_invalid_expiration_date_in_AMS_policy_service() {
		policyServiceEndPt = policyServiceEndPt + "/policies";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(obj1).log().all().when()
				.post(policyServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for invalid expiration date in AMS policy service")
	public void I_validate_the_response_of_the_post_call_for_invalid_expiration_date_in_AMS_policy_service(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("invalid policy number in AMS policy service")
	public void invalid_policy_number_in_AMS_policy_service() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/AMS/policyService/amsPolicyService_invalidPolicyNumber.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with invalid policy number in AMS policy service")
	public void I_send_a_post_request_with_invalid_policy_number_in_AMS_policy_service() {
		policyServiceEndPt = policyServiceEndPt + "/policies";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(obj1).log().all().when()
				.post(policyServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for invalid policy number in AMS policy service")
	public void I_validate_the_response_of_the_post_call_for_invalid_policy_number_in_AMS_policy_service(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("invalid policy type code in AMS policy service")
	public void invalid_policy_type_code_in_AMS_policy_service() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/AMS/policyService/amsPolicyService_invalidPolicyTypeCode.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with invalid policy type code in AMS policy service")
	public void I_send_a_post_request_with_invalid_policy_type_code_in_AMS_policy_service() {
		policyServiceEndPt = policyServiceEndPt + "/policies";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(obj1).log().all().when()
				.post(policyServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for invalid policy type code in AMS policy service")
	public void I_validate_the_response_of_the_post_call_for_invalid_policy_type_code_in_AMS_policy_service(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("invalid policy source in AMS policy service")
	public void invalid_policy_source_in_AMS_policy_service() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/AMS/policyService/amsPolicyService_invalidPolicySource.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with invalid policy source in AMS policy service")
	public void I_send_a_post_request_with_invalid_policy_source_in_AMS_policy_service() {
		policyServiceEndPt = policyServiceEndPt + "/policies";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(obj1).log().all().when()
				.post(policyServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for invalid policy source in AMS policy service")
	public void I_validate_the_response_of_the_post_call_for_invalid_policy_source_in_AMS_policy_service(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("invalid commission agreement id in AMS policy service")
	public void invalid_commission_agreement_id_in_AMS_policy_service() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/AMS/policyService/amsPolicyService_invalidCommissionAgreementId.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with invalid commission agreement id in AMS policy service")
	public void I_send_a_post_request_with_invalid_commission_agreement_id_in_AMS_policy_service() {
		policyServiceEndPt = policyServiceEndPt + "/policies";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(obj1).log().all().when()
				.post(policyServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for invalid commission agreement id in AMS policy service")
	public void I_validate_the_response_of_the_post_call_for_invalid_commission_agreement_id_in_AMS_policy_service(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("invalid employee lookup code in AMS policy service")
	public void invalid_employee_lookup_code_in_AMS_policy_service() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/AMS/policyService/amsPolicyService_invalidEmployeeLookupCode.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with invalid employee lookup code in AMS policy service")
	public void I_send_a_post_request_with_invalid_employee_lookup_code_in_AMS_policy_service() {
		policyServiceEndPt = policyServiceEndPt + "/policies";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(obj1).log().all().when()
				.post(policyServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for invalid employee lookup code in AMS policy service")
	public void I_validate_the_response_of_the_post_call_for_invalid_employee_lookup_code_in_AMS_policy_service(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("invalid lines in AMS policy service")
	public void invalid_lines_in_AMS_policy_service() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/AMS/policyService/amsPolicyService_invalidLines.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with invalid lines in AMS policy service")
	public void I_send_a_post_request_with_invalid_lines_in_AMS_policy_service() {
		policyServiceEndPt = policyServiceEndPt + "/policies";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(obj1).log().all().when()
				.post(policyServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for invalid lines in AMS policy service")
	public void I_validate_the_response_of_the_post_call_for_invalid_lines_in_AMS_policy_service(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("invalid issuing location code in AMS policy service")
	public void invalid_issuing_location_code_in_AMS_policy_service() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/AMS/policyService/amsPolicyService_invalidIssuingLocationCode.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with invalid issuing location code in AMS policy service")
	public void I_send_a_post_request_with_invalid_issuing_location_code_in_AMS_policy_service() {
		policyServiceEndPt = policyServiceEndPt + "/policies";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(obj1).log().all().when()
				.post(policyServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for invalid issuing location code in AMS policy service")
	public void I_validate_the_response_of_the_post_call_for_invalid_issuing_location_code_in_AMS_policy_service(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("invalid line type code in AMS policy service")
	public void invalid_line_type_code_in_AMS_policy_service() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/AMS/policyService/amsPolicyService_invalidLineTypeCode.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with invalid line type code in AMS policy service")
	public void I_send_a_post_request_with_invalid_line_type_code_in_AMS_policy_service() {
		policyServiceEndPt = policyServiceEndPt + "/policies";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(obj1).log().all().when()
				.post(policyServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for invalid line type code in AMS policy service")
	public void I_validate_the_response_of_the_post_call_for_invalid_line_type_code_in_AMS_policy_service(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("invalid profit center code in AMS policy service")
	public void invalid_profit_center_code_in_AMS_policy_service() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/AMS/policyService/amsPolicyService_invalidProfitCenterCode.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with invalid profit center code in AMS policy service")
	public void I_send_a_post_request_with_invalid_profit_center_code_in_AMS_policy_service() {
		policyServiceEndPt = policyServiceEndPt + "/policies";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(obj1).log().all().when()
				.post(policyServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for invalid profit center code in AMS policy service")
	public void I_validate_the_response_of_the_post_call_for_invalid_profit_center_code_in_AMS_policy_service(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("invalid status code in AMS policy service")
	public void invalid_status_code_in_AMS_policy_service() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/AMS/policyService/amsPolicyService_invalidStatusCode.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with invalid status code in AMS policy service")
	public void I_send_a_post_request_with_invalid_status_code_in_AMS_policy_service() {
		policyServiceEndPt = policyServiceEndPt + "/policies";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(obj1).log().all().when()
				.post(policyServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for invalid status code in AMS policy service")
	public void I_validate_the_response_of_the_post_call_for_invalid_status_code_in_AMS_policy_service(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("a AMS policy service endpoint URL to get list of commissions")
	public void a_AMS_policy_service_endpoint_URL_to_get_list_of_commissions(DataTable APIData) {
		List<Map<String, String>> lookup_Code = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = lookup_Code.get(0);
		policyServiceEndPt = policyServiceEndPt + "/commissions?lookup_code=" + dataRow.get("lookupCode");
		System.out.println("AMS Policy Service Endpoint for commissions :" + policyServiceEndPt);
	}

	@When("I send a GET request for AMS policy service endpoint URL to get list of commissions")
	public void I_send_a_GET_request_for_AMS_policy_service_endpoint_URL_to_get_list_of_commissions() {
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).when()
				.get(policyServiceEndPt).then().log().all();
	}

	@Then("validate the response of the AMS policy service endpoint to get list of commissions")
	public void validate_the_response_of_the_AMS_policy_service_endpoint_to_get_list_of_commissions(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}
	
	@Given("a AMS policy service endpoint URL for invalid token")
	public void a_AMS_policy_service_endpoint_URL_for_invalid_token(DataTable APIData) {
		List<Map<String, String>> lookup_Code = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = lookup_Code.get(0);
		policyServiceEndPt = policyServiceEndPt + "/commissions?lookup_code=" + dataRow.get("lookupCode");
		System.out.println("AMS Policy Service Endpoint for commissions :" + policyServiceEndPt);
	
	}

	@When("I send a GET request for AMS policy service endpoint URL for invalid token")
	public void I_send_a_GET_request_for_AMS_carrier_service_endpoint_URL_for_invalid_token() {
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).when()
				.get(policyServiceEndPt).then().log().all();

	}

	@Then("validate the response of the AMS policy service endpoint for invalid token")
	public void validate_the_response_of_the_AMS_policy_service_endpoint_for_invalid_token(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("a AMS policy service endpoint URL to get list of commissions with invalid lookup code")
	public void a_AMS_policy_service_endpoint_URL_to_get_list_of_commissions_with_invalid_lookup_code(DataTable APIData) {
		List<Map<String, String>> lookup_Code = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = lookup_Code.get(0);
		policyServiceEndPt = policyServiceEndPt + "/commissions?lookup_code=" + dataRow.get("lookupCode");
		System.out.println("AMS Policy Service Endpoint for commissions :" + policyServiceEndPt);
	}

	@When("I send a GET request for AMS policy service endpoint URL to get list of commissions with invalid lookup code")
	public void I_send_a_GET_request_for_AMS_policy_service_endpoint_URL_to_get_list_of_commissions_with_invalid_lookup_code() {
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).when()
				.get(policyServiceEndPt).then().log().all();
	}

	@Then("validate the response of the AMS policy service endpoint to get list of commissions with invalid lookup code")
	public void validate_the_response_of_the_AMS_policy_service_endpoint_to_get_list_of_commissions_with_invalid_lookup_code(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}
	
	@Given("an invalid AMS policy service endpoint URL")
	public void an_invalid_AMS_policy_service_endpoint_URL(DataTable APIData) {
		List<Map<String, String>> lookup_Code = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = lookup_Code.get(0);
		policyServiceEndPt = policyServiceEndPt + "/commi?lookup_code=" + dataRow.get("lookupCode");
		System.out.println("AMS Policy Service Endpoint for commissions :" + policyServiceEndPt);
	}

	@When("I send a GET request for invalid AMS policy service endpoint URL")
	public void I_send_a_GET_request_for_invalid_AMS_policy_service_endpoint_URL() {
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).when()
				.get(policyServiceEndPt).then().log().all();
	}

	@Then("validate the response of the invalid AMS policy service endpoint URL")
	public void validate_the_response_of_the_invalid_AMS_policy_service_endpoint_URL(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}
	
	@Given("a AMS policy service endpoint URL to get list of policies using client lookup code")
	public void a_AMS_policy_service_endpoint_URL_to_get_list_of_policies_using_client_lookup_code(DataTable APIData) {
		List<Map<String, String>> lookup_Code = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = lookup_Code.get(0);
		policyServiceEndPt = policyServiceEndPt + "/policies/account?client_lookup_code=" + dataRow.get("clientLookupCode");
		System.out.println("AMS Policy Service Endpoint for commissions :" + policyServiceEndPt);
	}

	@When("I send a GET request for AMS policy service endpoint URL to get list of policies using client lookup code")
	public void I_send_a_GET_request_for_AMS_policy_service_endpoint_URL_to_get_list_of_policies_using_client_lookup_code() {
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).when()
				.get(policyServiceEndPt).then().log().all();
	}

	@Then("validate the response of the AMS policy service endpoint to get list of policies using client lookup code")
	public void validate_the_response_of_the_AMS_policy_service_endpoint_to_get_list_of_policies_using_client_lookup_code(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}
	
	@Given("a AMS policy service endpoint URL to get list of policies using invalid end point")
	public void a_AMS_policy_service_endpoint_URL_to_get_list_of_policies_using_invalid_end_point(DataTable APIData) {
		List<Map<String, String>> lookup_Code = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = lookup_Code.get(0);
		policyServiceEndPt = policyServiceEndPt + "/policies/acco?client_lookup_code=" + dataRow.get("clientLookupCode");
		System.out.println("AMS Policy Service Endpoint for commissions :" + policyServiceEndPt);
	}

	@When("I send a GET request for AMS policy service endpoint URL to get list of policies using invalid end point")
	public void I_send_a_GET_request_for_AMS_policy_service_endpoint_URL_to_get_list_of_policies_using_invalid_end_point() {
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).when()
				.get(policyServiceEndPt).then().log().all();
	}

	@Then("validate the response of the AMS policy service endpoint to get list of policies using invalid end point")
	public void validate_the_response_of_the_AMS_policy_service_endpoint_to_get_list_of_policies_using_invalid_end_point(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}
}