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

public class QuoteServiceStepDefHO3Flow {
	public static WebDriver driver;
	public static Properties prop;

	protected ValidatableResponse validatableResponse;
	protected String token;
	protected String obj;
	protected String obj1;

	public QuoteServiceStepDefHO3Flow() {
		prop = TestInitialize.getProperties();
	}

	protected String quoteServiceEndPt = "https://api-qa.nonprod.rateins.com";
	protected String quoteServicetokenEndPt = "https://gr.oktapreview.com/oauth2/ausvtetp64jEl2AuE0h7/v1/token";
    protected String client_id1 = System.getenv("AUTOMATION_LEAD_SERVICE_CLIENT_ID");
	protected String client_secret1 = System.getenv("AUTOMATION_LEAD_SERVICE_CLIENT_SECRET");
	
	@Given("insurer and coinsurer details in HO3Flow")
	public void insurer_and_coinsurer_details_in_HO3Flow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI_HO3Flow/insurerCoinsurer_validDetails.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@And("a valid token in HO3 flow")
	public void a_valid_token_in_HO3_flow() {
		token = given().formParam("client_id", client_id1).formParam("client_secret", client_secret1)
				.formParam("grant_type", "client_credentials").log().all().when().post(quoteServicetokenEndPt).then()
				.log().all().extract().path("access_token");

		System.out.println("Token: " + token);
	}

	
	@When("I send a post request with valid insurer and coinsurer details")
	public void I_send_a_post_request_with_valid_insurer_and_coinsurer_details() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}

	
	@Then("I validate the response of the post call in HO3 flow")
	public void I_validate_the_response_of_the_post_call_in_HO3_flow(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@And("an invalid token id in HO3 flow")
	public void an_invalid_token_id_in_HO3_flow() {
		token= "invalidTokencode";
		System.out.println("Token: "+ token);
	}
	
	@When("I send a post request with valid insurer and coinsurer details with invalid token")
    public void I_send_a_post_request_with_valid_insurer_and_coinsurer_details_with_invalid_token(){
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(obj1)
	               .log().all()
	        .when()
	              .post(quoteServiceEndPt)
	        .then()
	               .assertThat().statusCode(401).log().all();
    }
	
	@Then("I validate the response of the post call in HO3 flow with invalid token")
	public void I_validate_the_response_of_the_post_call_in_HO3_flow_with_invalid_token(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}
	
	@Given("insurer details with invalid firstname in HO3Flow")
	public void insurer_details_with_invalid_firstname_in_HO3Flow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI_HO3Flow/insurer_invalidFirstname.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }

	@When("I send a post request with invalid firstname of insurer")
	public void I_send_a_post_request_with_invalid_firstname_of_insurer() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}

	
	@Then("I validate the response of the post call in HO3 flow with invalid firstname")
	public void I_validate_the_response_of_the_post_call_in_HO3_flow_with_invalid_firstname(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("insurer details with invalid DOB in HO3Flow")
	public void insurer_details_with_invalid_DOB_in_HO3Flow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI_HO3Flow/insurer_invalidDOB.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }

	@When("I send a post request with invalid DOB of insurer")
	public void I_send_a_post_request_with_invalid_DOB_of_insurer() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}

	
	@Then("I validate the response of the post call in HO3 flow with invalid DOB")
	public void I_validate_the_response_of_the_post_call_in_HO3_flow_with_invalid_DOB(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("insurer details with invalid lead id in HO3Flow")
	public void insurer_details_with_invalid_lead_id_in_HO3Flow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI_HO3Flow/insurer_invalidLeadID.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }

	@When("I send a post request with invalid lead id")
	public void I_send_a_post_request_with_invalid_lead_id() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}

	
	@Then("I validate the response of the post call in HO3 flow with invalid lead id")
	public void I_validate_the_response_of_the_post_call_in_HO3_flow_with_invalid_lead_id(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("insurer details with invalid streetname in HO3Flow")
	public void insurer_details_with_invalid_streetname_in_HO3Flow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI_HO3Flow/insurer_invalidStreetname.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }

	@When("I send a post request with invalid streetname of insurer")
	public void I_send_a_post_request_with_invalid_streetname() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}

	
	@Then("I validate the response of the post call in HO3 flow with invalid streetname")
	public void I_validate_the_response_of_the_post_call_in_HO3_flow_with_invalid_streetname(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("insurer details with invalid zip in HO3Flow")
	public void insurer_details_with_invalid_zip_in_HO3Flow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI_HO3Flow/insurer_invalidStreetname.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }

	@When("I send a post request with invalid zip")
	public void I_send_a_post_request_with_invalid_zip() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}

	
	@Then("I validate the response of the post call in HO3 flow with invalid Coinsurer firstname")
	public void I_validate_the_response_of_the_post_call_in_HO3_flow_with_invalid_Coinsurer_firstname(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("insurer details with invalid Coinsurer firstname in HO3Flow")
	public void insurer_details_with_invalid_Coinsurer_firstname_in_HO3Flow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI_HO3Flow/coinsurer_invalidFirstname.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }

	@When("I send a post request with invalid Coinsurer firstname")
	public void I_send_a_post_request_with_invalid_Coinsurer_firstname() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}
	
	@Then("I validate the response of the post call in HO3 flow with invalid zip")
	public void I_validate_the_response_of_the_post_call_in_HO3_flow_with_invalid_zip(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("insurer details with invalid Coinsurer DOB in HO3Flow")
	public void insurer_details_with_invalid_Coinsurer_DOB_in_HO3Flow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI_HO3Flow/coinsurer_invalidDOB.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }

	@When("I send a post request with invalid Coinsurer DOB")
	public void I_send_a_post_request_with_invalid_Coinsurer_DOB() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}
	
	@Then("I validate the response of the post call in HO3 flow with invalid Coinsurer DOB")
	public void I_validate_the_response_of_the_post_call_in_HO3_flow_with_invalid_Coinsurer_DOB(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("insurer details with invalid streetnumber in HO3Flow")
	public void insurer_details_with_invalid_streetnumber_in_HO3Flow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI_HO3Flow/insurer_invalidStreetnumber.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }

	@When("I send a post request with invalid streetnumber of insurer")
	public void I_send_a_post_request_with_invalid_streetnumber() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}
	
	@Then("I validate the response of the post call in HO3 flow with invalid streetnumber")
	public void I_validate_the_response_of_the_post_call_in_HO3_flow_with_invalid_streetnumber(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("insurer details with invalid state in HO3Flow")
	public void insurer_details_with_invalid_state_in_HO3Flow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI_HO3Flow/insurer_invalidState.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }

	@When("I send a post request with invalid state of insurer")
	public void I_send_a_post_request_with_invalid_state() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}
	
	@Then("I validate the response of the post call in HO3 flow with invalid state")
	public void I_validate_the_response_of_the_post_call_in_HO3_flow_with_invalid_state(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("insurer details with invalid purchase date in HO3Flow")
	public void insurer_details_with_invalid_purchase_date_in_HO3Flow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI_HO3Flow/insurer_invalidPurchaseDate.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }

	@When("I send a post request with invalid purchase date")
	public void I_send_a_post_request_with_invalid_purchase_date() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}
	
	@Then("I validate the response of the post call in HO3 flow with invalid purchase date")
	public void I_validate_the_response_of_the_post_call_in_HO3_flow_with_invalid_purchase_date(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("insurer details with invalid city in HO3Flow")
	public void insurer_details_with_invalid_city_in_HO3Flow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI_HO3Flow/insurer_invalidCity.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }

	@When("I send a post request with invalid city")
	public void I_send_a_post_request_with_invalid_city() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}
	
	@Then("I validate the response of the post call in HO3 flow with invalid city")
	public void I_validate_the_response_of_the_post_call_in_HO3_flow_with_invalid_city(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("insurer details with invalid marital status in HO3Flow")
	public void insurer_details_with_invalid_marital_status_in_HO3Flow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI_HO3Flow/insurer_invalidMaritalStatus.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }

	@When("I send a post request with invalid marital status")
	public void I_send_a_post_request_with_invalid_marital_status() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}
	
	@Then("I validate the response of the post call in HO3 flow with invalid marital status")
	public void I_validate_the_response_of_the_post_call_in_HO3_flow_with_invalid_marital_status(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("insurer details with invalid phone number in HO3Flow")
	public void insurer_details_with_invalid_phone_number_in_HO3Flow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI_HO3Flow/insurer_invalidPhoneNumber.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }

	@When("I send a post request with invalid phone number")
	public void I_send_a_post_request_with_invalid_phone_number() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}
	
	@Then("I validate the response of the post call in HO3 flow with invalid phone number")
	public void I_validate_the_response_of_the_post_call_in_HO3_flow_with_invalid_phone_number(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
}