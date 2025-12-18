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

public class QuoteServiceStepDefAutoFlow {
	public static WebDriver driver;
	public static Properties prop;

	protected ValidatableResponse validatableResponse;
	protected String token;
	protected String obj;
	protected String obj1;

	public QuoteServiceStepDefAutoFlow() {
		prop = TestInitialize.getProperties();
	}

	protected String quoteServiceEndPt = "https://api-qa.nonprod.rateins.com";
	protected String quoteServicetokenEndPt = "https://gr.oktapreview.com/oauth2/ausvtetp64jEl2AuE0h7/v1/token";
    protected String client_id1 = System.getenv("AUTOMATION_LEAD_SERVICE_CLIENT_ID");
	protected String client_secret1 = System.getenv("AUTOMATION_LEAD_SERVICE_CLIENT_SECRET");
	
	@Given("a health check end point URL")
	public void a_healthCheckURL() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/healthcheck";
		System.out.println("Quote Service Health check Endpoint :" + quoteServiceEndPt);

	}

	@When("I send a GET request for the health check URL")
	public void sendGETRequest_HealthCheckURL() {
		validatableResponse = given().contentType(ContentType.JSON).when().get(quoteServiceEndPt).then().log().all();

	}

	@Then("validate the response of the Health check endpoint with status code")
	public void verifyHealthCheckURLResponseCode(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		validatableResponse.assertThat().body(equalTo(dataRow.get("body")));
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));

	}

	@Given("an invalid health check end point URL")
	public void invalid_healthCheckURL() {
		quoteServiceEndPt = quoteServiceEndPt + "/quote/healthche";
		System.out.println("Quote Service Health check Endpoint :" + quoteServiceEndPt);

	}

	@Then("validate the response of the invalid Health check endpoint with status code")
	public void verifyInvalidHealthCheckURLResponseCode(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));

	}

	@Given("one driver and one vehicle details in AutoFlow")
	public void one_driver_and_one_vehicle_details_in_AutoFlow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI/oneDriverOneVehicle.json"));
		obj = new String(b);
		System.out.println(obj);
	}

	@And("a valid token")
	public void a_valid_token() {
		token = given().formParam("client_id", client_id1).formParam("client_secret", client_secret1)
				.formParam("grant_type", "client_credentials").log().all().when().post(quoteServicetokenEndPt).then()
				.log().all().extract().path("access_token");

		System.out.println("Token: " + token);
	}

	@When("I send a post request with one driver and one vehicle details")
	public void I_send_a_post_request_with_one_driver_and_one_vehicle_details() {
		System.out.println("Obj in this method is:" + obj);
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(obj)
				.log().all().when().post(quoteServiceEndPt).then().assertThat().statusCode(200).log().all();
	}

	@Then("I validate the response of the post call for One Driver and One vehicle in Auto flow")
	public void I_validate_the_response_of_the_post_call_for_One_Driver_and_One_vehicle(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}

	@Given("three drivers and three vehicles in AutoFlow")
	public void the_additional_driver_details_in_AutoFlow() throws IOException {

		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI/threeDriversThreeVehicles.json"));
		obj1 = new String(b);
		System.out.println(obj1);

	}

	@When("I send a post request with three driver and three vehicle details")
	public void I_send_a_post_request_with_three_driver_and_three_vehicle_details() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().assertThat().statusCode(200).log().all();
	}

	@Then("I validate the response of the post call for three Drivers and three vehicles in Auto flow")
	public void I_validate_the_response_of_the_post_call_for_three_Drivers_and_three_vehicles(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}

	@Given("five drivers and five vehicles in AutoFlow")
	public void five_drivers_and_five_vehicles_in_AutoFlow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI/fiveDriversFiveVehicles.json"));
		obj1 = new String(b);
		System.out.println(obj1);

	}

	@When("I send a post request with five driver and five vehicle details")
	public void I_send_a_post_request_with_five_driver_and_five_vehicle_details() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().assertThat().statusCode(200).log().all();
	}

	@Then("I validate the response of the post call for five Drivers and five vehicles in Auto flow")
	public void I_validate_the_response_of_the_post_call_for_five_Drivers_and_five_vehicles(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}
	
	@And("an invalid token id")
	public void an_invalid_token() {
		token= "invalidTokencode";
		System.out.println("Token: "+ token);
	}
	
	@When("I send a post request with one driver and one vehicle details with invalid token")
    public void I_send_a_post_request_with_invalid_token_details(){
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
    	validatableResponse = 
	        given()
	        	   .header("Authorization", "Bearer " + token)
	               .contentType(ContentType.JSON)
	               .body(obj)
	               .log().all()
	        .when()
	              .post(quoteServiceEndPt)
	        .then()
	               .assertThat().statusCode(401).log().all();
    }
	
	@Then("I validate the response of the post call for one driver and one vehicle with invalid token in Auto flow")
	public void I_validate_the_response_of_the_post_call_for_one_Driver_and_one_vehicle_with_invalid_token(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
	}
	
	
	@Given("one driver with invalid firstname and one vehicle in AutoFlow")
	public void one_driver_with_invalid_firstname_and_one_vehicle_in_AutoFlow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI/oneDriverOneVehicle_InvalidFirstName.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with one driver and one vehicle details with invalid firstname")
	public void I_send_a_post_request_with_one_driver_and_one_vehicle_details_with_invalid_firstname() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for one driver and one vehicle with invalid firstname in Auto flow")
	public void I_validate_the_response_of_the_post_call_for_one_driver_and_one_vehicle_with_invalid_firstname_in_Auto_flow(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("one driver with invalid lastname and one vehicle in AutoFlow")
	public void one_driver_with_invalid_lastname_and_one_vehicle_in_AutoFlow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI/oneDriverOneVehicle_InvalidLastName.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with one driver and one vehicle details with invalid lastname")
	public void I_send_a_post_request_with_one_driver_and_one_vehicle_details_with_invalid_lastname() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for one driver and one vehicle with invalid lastname in Auto flow")
	public void I_validate_the_response_of_the_post_call_for_one_driver_and_one_vehicle_with_invalid_lastname_in_Auto_flow(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("one driver with invalid DOB and one vehicle in AutoFlow")
	public void one_driver_with_invalid_DOB_and_one_vehicle_in_AutoFlow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI/oneDriverOneVehicle_InvalidDOB.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with one driver and one vehicle details with invalid DOB")
	public void I_send_a_post_request_with_one_driver_and_one_vehicle_details_with_invalid_DOB() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for one driver and one vehicle with invalid DOB in Auto flow")
	public void I_validate_the_response_of_the_post_call_for_one_driver_and_one_vehicle_with_invalid_DOB_in_Auto_flow(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
		validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("five drivers with invalid firstname and one vehicle in AutoFlow")
	public void five_drivers_with_invalid_firstname_and_one_vehicle_in_AutoFlow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI/fiveDrivers_InvalidFirstName.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with five drivers and one vehicle details with invalid firstname")
	public void I_send_a_post_request_with_five_drivers_and_one_vehicle_details_with_invalid_firstname() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for five drivers and one vehicle with invalid firstname in AutoFlow")
	public void I_validate_the_response_of_the_post_call_for_five_drivers_and_one_vehicle_with_invalid_firstname_in_AutoFlow(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
				validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("five drivers with invalid DOB and one vehicle in AutoFlow")
	public void five_drivers_with_invalid_DOB_and_one_vehicle_in_AutoFlow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI/fiveDrivers_InvalidDOB.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with five drivers and one vehicle details with invalid DOB")
	public void I_send_a_post_request_with_five_drivers_and_one_vehicle_details_with_invalid_DOB() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for five drivers and one vehicle with invalid DOB in AutoFlow")
	public void I_validate_the_response_of_the_post_call_for_five_drivers_and_one_vehicle_with_invalid_DOB_in_AutoFlow(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
				validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("one driver and one vehicle with invalid miles in AutoFlow")
	public void one_driver_and_one_vehicle_with_invalid_miles_in_AutoFlow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI/oneDriverOneVehicle_InvalidMiles.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with one driver and one vehicle details with invalid miles")
	public void I_send_a_post_request_with_one_driver_and_one_vehicle_details_with_invalid_miles() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for one driver and one vehicle with invalid miles in AutoFlow")
	public void I_validate_the_response_of_the_post_call_for_one_driver_and_one_vehicle_with_invalid_miles_in_AutoFlow(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
				validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("five drivers and five vehicles with invalid miles in AutoFlow")
	public void five_drivers_and_five_vehicles_with_invalid_miles_in_AutoFlow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI/fiveDriversFiveVehicles_InvalidMiles.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with five drivers and five vehicles details with invalid miles")
	public void I_send_a_post_request_with_five_drivers_and_five_vehicles_details_with_invalid_miles() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for five drivers and five vehicles with invalid miles in AutoFlow")
	public void I_validate_the_response_of_the_post_call_for_five_drivers_and_five_vehicles_with_invalid_miles_in_AutoFlow(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
				validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("one driver and one vehicle with invalid ownership in AutoFlow")
	public void one_driver_and_one_vehicle_with_invalid_ownership_in_AutoFlow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI/oneDriverOneVehicle_InvalidOwnership.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with one driver and one vehicle details with invalid ownership")
	public void I_send_a_post_request_with_five_drivers_and_five_vehicles_details_with_invalid_ownership() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for one driver and one vehicle with invalid ownership in AutoFlow")
	public void I_validate_the_response_of_the_post_call_for_one_driver_and_one_vehicle_with_invalid_ownership_in_AutoFlow(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
				validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("five drivers and five vehicles with invalid ownership in AutoFlow")
	public void five_drivers_and_five_vehicles_with_invalid_ownership_in_AutoFlow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI/fiveDriversFiveVehicles_InvalidOwnership.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with five drivers and five vehicles with invalid ownership")
	public void I_send_a_post_request_with_five_drivers_and_five_vehicles_with_invalid_ownership() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for five drivers and five vehicles with invalid ownership in AutoFlow")
	public void I_validate_the_response_of_the_post_call_for_five_drivers_and_five_vehicles_with_invalid_ownership_in_AutoFlow(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
				validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	@Given("one driver and one vehicle with invalid marital status in AutoFlow")
	public void one_driver_and_one_vehicle_with_invalid_marital_status_in_AutoFlow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI/oneDriverOneVehicle_InvalidMaritalStatus.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with one driver and one vehicle details with invalid marital status")
	public void I_send_a_post_request_with_five_drivers_and_five_vehicles_details_with_invalid_marital_status() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for one driver and one vehicle with invalid marital status in AutoFlow")
	public void I_validate_the_response_of_the_post_call_for_one_driver_and_one_vehicle_with_invalid_marital_status_in_AutoFlow(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
				validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("one driver and one vehicle with invalid phone number in AutoFlow")
	public void one_driver_and_one_vehicle_with_invalid_phone_number_in_AutoFlow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI/oneDriverOneVehicle_InvalidPhoneNumber.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with one driver and one vehicle details with invalid phone number")
	public void I_send_a_post_request_with_five_drivers_and_five_vehicles_details_with_invalid_phone_number() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for one driver and one vehicle with invalid phone number in AutoFlow")
	public void I_validate_the_response_of_the_post_call_for_one_driver_and_one_vehicle_with_invalid_phone_number_in_AutoFlow(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
				validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("one driver and one vehicle with invalid zip in AutoFlow")
	public void one_driver_and_one_vehicle_with_invalid_zip_in_AutoFlow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI/oneDriverOneVehicle_InvalidZip.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with one driver and one vehicle details with invalid zip")
	public void I_send_a_post_request_with_five_drivers_and_five_vehicles_details_with_invalid_zip() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for one driver and one vehicle with invalid zip in AutoFlow")
	public void I_validate_the_response_of_the_post_call_for_one_driver_and_one_vehicle_with_invalid_zip_in_AutoFlow(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
				validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("one driver and one vehicle with invalid description in AutoFlow")
	public void one_driver_and_one_vehicle_with_invalid_description_in_AutoFlow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI/oneDriverOneVehicle_InvalidDescription.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with one driver and one vehicle details with invalid description")
	public void I_send_a_post_request_with_five_drivers_and_five_vehicles_details_with_invalid_description() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for one driver and one vehicle with invalid description in AutoFlow")
	public void I_validate_the_response_of_the_post_call_for_one_driver_and_one_vehicle_with_invalid_description_in_AutoFlow(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
				validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("one driver and one vehicle with invalid vin in AutoFlow")
	public void one_driver_and_one_vehicle_with_invalid_vin_in_AutoFlow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI/oneDriverOneVehicle_InvalidVin.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with one driver and one vehicle details with invalid vin")
	public void I_send_a_post_request_with_five_drivers_and_five_vehicles_details_with_invalid_vin() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for one driver and one vehicle with invalid vin in AutoFlow")
	public void I_validate_the_response_of_the_post_call_for_one_driver_and_one_vehicle_with_invalid_vin_in_AutoFlow(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
				validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
	
	@Given("one driver and one vehicle with invalid usage in AutoFlow")
	public void one_driver_and_one_vehicle_with_invalid_usage_in_AutoFlow() throws IOException {
		byte[] b = Files.readAllBytes(Paths.get(System.getProperty("user.dir"),
				"src/test/java/elementLocators/quoteServiceAPI/oneDriverOneVehicle_InvalidUsage.json"));
		obj1 = new String(b);
		System.out.println(obj1);
    }
	
	@When("I send a post request with one driver and one vehicle details with invalid usage")
	public void I_send_a_post_request_with_five_drivers_and_five_vehicles_details_with_invalid_usage() {
		quoteServiceEndPt = quoteServiceEndPt + "/quotes/request";
		validatableResponse = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
				.body(obj1).log().all().when().post(quoteServiceEndPt).then().log().all();
	}

	@Then("I validate the response of the post call for one driver and one vehicle with invalid usage in AutoFlow")
	public void I_validate_the_response_of_the_post_call_for_one_driver_and_one_vehicle_with_invalid_usage_in_AutoFlow(DataTable APIData) {
		List<Map<String, String>> apiData = APIData.asMaps(String.class, String.class);
		Map<String, String> dataRow = apiData.get(0);
		System.out.println("Body" + validatableResponse.log().all());
		validatableResponse.assertThat().statusCode(equalTo(Integer.parseInt(dataRow.get("statuscode"))));
				validatableResponse.assertThat().body("success",equalTo(Boolean.parseBoolean(dataRow.get("success"))));
	}
}