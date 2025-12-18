package stepDefinition.omsite.auto;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import elementLocators.omsite.auto.AdditionalDriverInfoPage;
import elementLocators.omsite.auto.AdditionalVehicleInfoPage;
import elementLocators.omsite.auto.DriverInfoPage;
import elementLocators.omsite.auto.GarageInfoPage;
import elementLocators.omsite.auto.QuotesPage;
import elementLocators.omsite.auto.VehicleInfoPage;
import elementLocators.omsite.ho3.AddingCoinsurerPage;
import utils.TestInitialize;

public class AutoStepDef {
	public static WebDriver driver;
	public static Properties prop;
		
	public AutoStepDef()
	{
		prop = TestInitialize.getProperties();
	}

	public static void initialization() {
		String browsername = prop.getProperty("browser");
		if (browsername.equals("chrome")) {
			driver = TestInitialize.getChromeDriver(prop);
		}
		DriverInfoPage driverInfoPage = new DriverInfoPage(driver);
		AdditionalDriverInfoPage additionalDriverInfoPage = new AdditionalDriverInfoPage(driver);
		GarageInfoPage garageInfoPage = new GarageInfoPage(driver);
		VehicleInfoPage vehicleInfoPage = new VehicleInfoPage(driver);
		AdditionalVehicleInfoPage additionalVehicleInfoPage = new AdditionalVehicleInfoPage(driver);
		QuotesPage quotesPage =new QuotesPage(driver);
	}
//*******************************Open the browser************************************
	@Given("Open the chrome browser")
	public void Open_the_chrome_browser() {
		try
		{
			initialization();			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

//*********************************Launch the Auto URL*********************************
	
	@When("launch the Auto flow url")
	public void launch_the_Auto_flow_url() {	
		driver.get(prop.getProperty("AutoURL"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(DriverInfoPage.cookieAlert));
		Boolean isCookieAlertPresent =  driver.findElements(DriverInfoPage.cookieAlert).size() > 0;
		if(isCookieAlertPresent == true)
		{
			DriverInfoPage.closeCookieAlert();
		}
	}
	
//**********************************Driver Info page***********************************
	
	@Then("validate whether user is on driverInfo page")
	public void validate_whether_user_is_on_driverInfo_page() {
		System.out.println("Title of the page is: " + driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains("Guaranteed Rate"));
	}
	
	@Then("validate whether user is able to enter valid user data in driverInfo page")
	public void validate_whether_user_is_able_to_enter_valid_user_data_in_driverInfo_page(DataTable DriverInfo) {
		try {
			System.out.println("Title of the page is: " + driver.getTitle());
			List<Map<String, String>> driverInfoData = DriverInfo.asMaps(String.class, String.class);
			Map<String, String> dataRow = driverInfoData.get(0);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfElementLocated(DriverInfoPage.firstName));
			DriverInfoPage.enterDriverFirstName(dataRow.get("FirstName"));
			DriverInfoPage.enterDriverLastName(dataRow.get("LastName"));
			DriverInfoPage.enterDriverDOBMonth(dataRow.get("Month"));
			DriverInfoPage.enterDriverDOBDay(dataRow.get("Day"));
			DriverInfoPage.enterDriverDOBYear(dataRow.get("Year"));
			DriverInfoPage.genderDropdown();
			DriverInfoPage.selectGender();
			DriverInfoPage.maritalStatusDropdown();
			DriverInfoPage.driverMaritalStatus();
			DriverInfoPage.enterPhoneNumber(dataRow.get("PhoneNumber"));
			boolean isEmailAddressPresent = driver.findElements(DriverInfoPage.emailAddress).size() > 0;
			if(isEmailAddressPresent == true)
			{
			DriverInfoPage.enterEmailAddress(dataRow.get("EmailAddress"));
			}
			Thread.sleep(2000);
			WebElement Nextbutton = driver.findElement(By.xpath("//*[contains(text(),'Next')]"));
			Assert.assertTrue(Nextbutton.isEnabled());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	@When("user enters invalid data in driver info page")
	public void user_enters_invalid_data_in_driver_info_page(DataTable InvalidDriverInfo) {
		try {
			List<Map<String, String>> invalidDriverInfoData = InvalidDriverInfo.asMaps(String.class, String.class);
			Map<String, String> dataRow = invalidDriverInfoData.get(0);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfElementLocated(DriverInfoPage.firstName));
			DriverInfoPage.enterDriverFirstName(dataRow.get("FirstName"));
			DriverInfoPage.enterDriverLastName(dataRow.get("LastName"));
			DriverInfoPage.enterDriverDOBMonth(dataRow.get("Month"));
			DriverInfoPage.enterDriverDOBDay(dataRow.get("Day"));
			DriverInfoPage.enterDriverDOBYear(dataRow.get("Year"));
			DriverInfoPage.genderDropdown();
			DriverInfoPage.selectGender();
			DriverInfoPage.maritalStatusDropdown();
			DriverInfoPage.driverMaritalStatus();
			DriverInfoPage.enterPhoneNumber(dataRow.get("PhoneNumber"));
			boolean isEmailAddressPresent = driver.findElements(DriverInfoPage.emailAddress).size() > 0;
			if(isEmailAddressPresent == true)
			{
			DriverInfoPage.enterEmailAddress(dataRow.get("EmailAddress"));
			}
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Then("validate whether user able to view the error message for invalid date of birth in driver info page")
	public void validate_whether_user_able_to_view_the_error_message_for_invalid_date_of_birth_in_driver_info_page() {
		try {
			Thread.sleep(4000);
			WebElement invalidDateErrorMessage = driver.findElement(By.xpath("//*[contains(text(),'Please enter a valid date')]"));
			Assert.assertTrue(invalidDateErrorMessage.isDisplayed());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	

	@And("validate whether the Next button is disabled in driver info page")
	public void validate_whether_the_Next_button_is_disabled_in_driver_info_page() {
		try {
			Thread.sleep(4000);
			WebElement disabledNextButton = driver.findElement(By.xpath("//button[@disabled and contains(text(),'Next')]"));
			Assert.assertTrue(disabledNextButton.isDisplayed());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@When("user enters invalid day in driver info page")
	public void user_enters_invalid_day_in_driver_info_page(DataTable InvalidDayInDriverInfo) {
		try {
			List<Map<String, String>> invalidDayDriverInfoData = InvalidDayInDriverInfo.asMaps(String.class, String.class);
			Map<String, String> dataRow = invalidDayDriverInfoData.get(0);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfElementLocated(DriverInfoPage.firstName));
			DriverInfoPage.enterDriverFirstName(dataRow.get("FirstName"));
			DriverInfoPage.enterDriverLastName(dataRow.get("LastName"));
			DriverInfoPage.enterDriverDOBMonth(dataRow.get("Month"));
			DriverInfoPage.enterDriverDOBDay(dataRow.get("Day"));
			DriverInfoPage.enterDriverDOBYear(dataRow.get("Year"));
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@When("user enters invalid month in driver info page")
	public void user_enters_invalid_month_in_driver_info_page(DataTable InvalidMonthInDriverInfo) {
		try {
			List<Map<String, String>> invalidMonthDriverInfoData = InvalidMonthInDriverInfo.asMaps(String.class, String.class);
			Map<String, String> dataRow = invalidMonthDriverInfoData.get(0);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfElementLocated(DriverInfoPage.firstName));
			DriverInfoPage.enterDriverFirstName(dataRow.get("FirstName"));
			DriverInfoPage.enterDriverLastName(dataRow.get("LastName"));
			DriverInfoPage.enterDriverDOBMonth(dataRow.get("Month"));
			DriverInfoPage.enterDriverDOBDay(dataRow.get("Day"));
			DriverInfoPage.enterDriverDOBYear(dataRow.get("Year"));
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@When("user enters invalid year in driver info page")
	public void user_enters_invalid_year_in_driver_info_page(DataTable InvalidYearInDriverInfo) {
		try {
			List<Map<String, String>> invalidYearDriverInfoData = InvalidYearInDriverInfo.asMaps(String.class, String.class);
			Map<String, String> dataRow = invalidYearDriverInfoData.get(0);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfElementLocated(DriverInfoPage.firstName));
			DriverInfoPage.enterDriverFirstName(dataRow.get("FirstName"));
			DriverInfoPage.enterDriverLastName(dataRow.get("LastName"));
			DriverInfoPage.enterDriverDOBMonth(dataRow.get("Month"));
			DriverInfoPage.enterDriverDOBDay(dataRow.get("Day"));
			DriverInfoPage.enterDriverDOBYear(dataRow.get("Year"));
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@When("user enters invalid phone number in driver info page")
	public void user_enters_invalid_phone_number_in_driver_info_page(DataTable InvalidPhoneNumberInDriverInfo) {
		try {
			List<Map<String, String>> invalidPhoneNumberDriverInfoData = InvalidPhoneNumberInDriverInfo.asMaps(String.class, String.class);
			Map<String, String> dataRow = invalidPhoneNumberDriverInfoData.get(0);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfElementLocated(DriverInfoPage.firstName));
			DriverInfoPage.enterDriverFirstName(dataRow.get("FirstName"));
			DriverInfoPage.enterDriverLastName(dataRow.get("LastName"));
			DriverInfoPage.enterDriverDOBMonth(dataRow.get("Month"));
			DriverInfoPage.enterDriverDOBDay(dataRow.get("Day"));
			DriverInfoPage.enterDriverDOBYear(dataRow.get("Year"));
			DriverInfoPage.genderDropdown();
			DriverInfoPage.selectGender();
			DriverInfoPage.maritalStatusDropdown();
			DriverInfoPage.driverMaritalStatus();
			DriverInfoPage.enterPhoneNumber(dataRow.get("PhoneNumber"));
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Then("validate whether user is able to navigate to Would you like to add additional driver page")
	public void validate_whether_user_is_able_to_navigate_to_Would_you_like_to_add_additional_driver_page() {

			WebElement Nextbutton = driver.findElement(By.xpath("//button[contains(@data-testid,'next-btn')]"));
			Assert.assertTrue(Nextbutton.isEnabled());
			Nextbutton.click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@data-testid,'add-driver-header-h2')]")));
			Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@data-testid,'add-driver-header-h2')]")).isDisplayed());
	}	

//************************************Garage Info page*********************************
	@Then("validate whether user is able to navigate to Garage Info collection page without adding additional driver info")
	public void validate_whether_user_is_able_to_navigate_to_garage_info_collection_page_without_adding_additional_driver_info() {
			GarageInfoPage.clickNoRadioButton();
			Assert.assertTrue(GarageInfoPage.noRadioButton().isEnabled());
			GarageInfoPage.clickNext();
	}

	@Then("validate whether user is able to enter valid user data in GarageInfo page")
	public void validate_whether_user_is_able_to_enter_valid_user_data_in_garage_info_page(DataTable garageAddress) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		List<Map<String, String>> validAddressInfoData = garageAddress.asMaps(String.class, String.class);
		Map<String, String> dataRow = validAddressInfoData.get(0);
		GarageInfoPage.enterAddress(dataRow.get("Address"));
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.TAB).sendKeys(Keys.TAB).build().perform();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//h2[contains(@data-testid,'add-garaging-header-h2')]")).click();
		ExpectedConditions.visibilityOfElementLocated(GarageInfoPage.city);
		GarageInfoPage.enterCity(dataRow.get("City"));
		actions.moveToElement(GarageInfoPage.state());
		GarageInfoPage.selectState();
		GarageInfoPage.selectStateTexas();
		GarageInfoPage.enterzipcode(dataRow.get("ZipCode"));
		
	}

	@Then("validate whether user is able to enter valid address in GarageInfo page")
	public void validate_whether_user_is_able_to_enter_valid_address_in_garage_info_page(DataTable validAddress) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		List<Map<String, String>> invalidAddress = validAddress.asMaps(String.class, String.class);
		Map<String, String> dataRow = invalidAddress.get(0);
		GarageInfoPage.enterAddress(dataRow.get("Address"));
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.TAB).sendKeys(Keys.TAB).build().perform();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//h2[contains(@data-testid,'add-garaging-header-h2')]")).click();
		ExpectedConditions.visibilityOfElementLocated(GarageInfoPage.city);
		GarageInfoPage.enterCity(dataRow.get("City"));
		GarageInfoPage.selectState();
		GarageInfoPage.selectStateOhio();
		GarageInfoPage.enterzipcode(dataRow.get("ZipCode"));
	}

	@Then("validate user navigation when enter invalid property address in Garage info page")
	public void validate_user_navigation_when_enter_invalid_property_address_in_Garage_info_page(
			DataTable invalidGarageAddress) {
			List<Map<String, String>> invalidAddress = invalidGarageAddress.asMaps(String.class, String.class);
			Map<String, String> dataRow = invalidAddress.get(0);
			GarageInfoPage.enterAddress(dataRow.get("Address"));
			GarageInfoPage.enterSuitenumber(dataRow.get("SuiteNumber"));
			GarageInfoPage.enterCity(dataRow.get("City"));
			GarageInfoPage.selectState();
			GarageInfoPage.selectStateTexas();
			GarageInfoPage.enterzipcode(dataRow.get("ZipCode"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfElementLocated(GarageInfoPage.disabledNextButton));
			Assert.assertTrue(GarageInfoPage.disabledNextButton().isDisplayed());
	}

	@Then("validate user navigation when enter only city and zipcode in property address")
	public void validate_user_navigation_when_enter_only_city_and_zipcode_in_property_address(
			DataTable partialGarageAddress) {
			List<Map<String, String>> partialAddress = partialGarageAddress.asMaps(String.class, String.class);
			Map<String, String> dataRow = partialAddress.get(0);
			GarageInfoPage.enterCity(dataRow.get("City"));
			GarageInfoPage.enterzipcode(dataRow.get("ZipCode"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfElementLocated(GarageInfoPage.disabledNextButton));
			Assert.assertTrue(GarageInfoPage.disabledNextButton().isDisplayed());
	}

//************************************Vehicle Info page*******************************

	@Then("validate whether user is able to navigate to Vehicle info page when clicked on Next")
	public void validate_whether_user_is_able_to_navigate_to_vehicle_info_page_when_clicked_on_next() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(VehicleInfoPage.nextButton));
	    Assert.assertTrue(VehicleInfoPage.nextButton().isEnabled());
        VehicleInfoPage.clickNextButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(VehicleInfoPage.vehicleInfoPageTitle));
        Assert.assertTrue(VehicleInfoPage.vehicleInfopageTitle().isDisplayed());
	}
	
	@And("validate whether the Next button is disabled in vehicle info page")
	public void validate_whether_the_Next_button_is_disabled_in_vehicle_info_page() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(VehicleInfoPage.disabledNextButton));
		Assert.assertTrue(VehicleInfoPage.disabledNextButton().isDisplayed());
	}
	
	@Then("validate whether user is able to enter valid data in all fields of vehicle info page")
	public void validate_whether_user_is_able_to_enter_valid_data_in_all_fields_of_vehicle_info_page(DataTable validVehicleInfo) {
		
		List<Map<String,String>> vehicleDetails= validVehicleInfo.asMaps(String.class,String.class);
        Map<String, String> dataRow = vehicleDetails.get(0);
        String strYear = dataRow.get("Year");
        String strMake = dataRow.get("Make");
        String strModel = dataRow.get("Model");
        String strSubModel = dataRow.get("SubModel");
        
		//select Year
		VehicleInfoPage.selectYear();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement year = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[contains(text(),'"+strYear+"')]")));
		year.click();
		
		//Select Make
		VehicleInfoPage.selectMake();
		WebElement make = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[contains(text(),'"+strMake+"')]")));
		make.click();
		
		//Select Model
		VehicleInfoPage.selectModel();
		WebElement model = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[contains(text(),'"+strModel+"')]")));
		model.click();
		
		//Select Trim or Sub model
		VehicleInfoPage.selectTrimOrSubModel();
		WebElement submodel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[contains(text(),'"+strSubModel+"')]")));
		submodel.click();
		
	}

	@Then("validate whether user able to select only vehicle year in vehicle info page")
	public void validate_whether_user_able_to_select_only_vehicle_year_in_vehicle_info_page(DataTable validVehicleInfo) {
		List<Map<String,String>> vehicleDetails= validVehicleInfo.asMaps(String.class,String.class);
        Map<String, String> dataRow = vehicleDetails.get(0);
		String strYear = dataRow.get("Year");
		
		//Select Year
		VehicleInfoPage.selectYear();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement year = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[contains(text(),'"+strYear+"')]")));
		year.click();
		
	}
	
	@Then("validate whether user able to select only vehicle year and make in vehicle info page")
	public void validate_whether_user_able_to_select_only_vehicle_year_and_make_in_vehicle_info_page(DataTable validVehicleInfo) {
		List<Map<String,String>> vehicleDetails= validVehicleInfo.asMaps(String.class,String.class);
        Map<String, String> dataRow = vehicleDetails.get(0);
		String strYear = dataRow.get("Year");
		String strMake = dataRow.get("Make");
		
		//Select Year
		VehicleInfoPage.selectYear();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement year = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[contains(text(),'"+strYear+"')]")));
		year.click();
		
		//Select Make
		VehicleInfoPage.selectMake();
		WebElement make = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[contains(text(),'"+strMake+"')]")));
		make.click();
	}
	
	
	@Then("validate whether user able to select only vehicle year and make and model in vehicle info page")
	public void validate_whether_user_able_to_select_only_vehicle_year_and_make_and_model_in_vehicle_info_page(DataTable validVehicleInfo) {
		List<Map<String,String>> vehicleDetails= validVehicleInfo.asMaps(String.class,String.class);
        Map<String, String> dataRow = vehicleDetails.get(0);
        String strYear = dataRow.get("Year");
        String strMake = dataRow.get("Make");
        String strModel = dataRow.get("Model");
		
		//Select Year
        VehicleInfoPage.selectYear();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement year = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[contains(text(),'"+strYear+"')]")));
		year.click();
		
		//Select Make
		VehicleInfoPage.selectMake();
		WebElement make = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[contains(text(),'"+strMake+"')]")));
		make.click();
		
		//Select Model
		VehicleInfoPage.selectModel();
		WebElement model = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[contains(text(),'"+strModel+"')]")));
		model.click();
		
	}
	
	@Then("validate whether user is able to click on Next and navigate to more vehicle details page")
	public void validate_whether_user_is_able_to_click_on_next_and_navigate_to_more_vehicle_details_page() {
		Assert.assertTrue(VehicleInfoPage.nextButton().isEnabled());
		VehicleInfoPage.clickNextButton();
		Assert.assertTrue(VehicleInfoPage.moreDetailsOfVehicle().isDisplayed());
	}
	
	@Then("validate whether user is able to enter valid data in all fields of more vehicle details page")
	public void validate_whether_user_is_able_to_enter_valid_data_in_all_fields_of_more_vehicle_details_page(DataTable validMoreVehicleInfo) {
		List<Map<String,String>> moreVehicleDetails= validMoreVehicleInfo.asMaps(String.class,String.class);
        Map<String, String> dataRow = moreVehicleDetails.get(0);
        String vehicleOwnership = dataRow.get("VehicleOwnership");
        String vehicleUsage = dataRow.get("VehicleUsage");
        String noOfMiles = dataRow.get("NoOfMiles");
        
		VehicleInfoPage.selectVehicleOwnership();
		driver.findElement(By.xpath("//li[contains(text(),'"+vehicleOwnership+"')]")).click();
		
		VehicleInfoPage.selectVehicleUsage();
		driver.findElement(By.xpath("//li[contains(text(),'"+vehicleUsage+"')]")).click();
		
		VehicleInfoPage.enterNoOfMiles(noOfMiles);
	}
	
//********************************Additional Vehicle Info page***********************
	@Then("validate whether user is able to click on Next and navigate to Add Additional vehicle details page")
	public void validate_whether_user_is_able_to_click_on_next_and_navigate_to_add_additional_vehicle_details_page() {
		Assert.assertTrue(VehicleInfoPage.nextButton().isEnabled());
		VehicleInfoPage.clickNextButton();
	}

	@Given("User is on Add Additional vehicle details page")
	public void user_is_on_add_additional_vehicle_details_page() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AdditionalVehicleInfoPage.additionalVehicleInfoPageTitle));
		Assert.assertTrue(AdditionalVehicleInfoPage.additionalVehicleInfoPageTitle().isDisplayed());
	}

	@When("User clicks on No radio button")
	public void user_clicks_on_no_radio_button() {
		Assert.assertTrue(AdditionalVehicleInfoPage.noRadioButton().isEnabled());
		AdditionalVehicleInfoPage.clickNoRadioButton();
	}

	@Then("validate whether user is able to click on Yes and navigate to addtional vehicle info collection page")
	public void validate_whether_user_is_able_to_click_on_yes_and_navigate_to_addtional_vehicle_info_collection_page() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AdditionalVehicleInfoPage.clickYesRadioButton));
		Assert.assertTrue(AdditionalVehicleInfoPage.yesRadioButton().isEnabled());
		AdditionalVehicleInfoPage.clickYesRadioButton();
		VehicleInfoPage.clickNextButton();
	}

	@Then("validate whether user is able to enter valid details in Additional vehicle page and more details of vehicle pages")
	public void validate_whether_user_is_able_to_enter_valid_details_in_additional_vehicle_page_and_more_details_of_vehicle_pages(DataTable additionalVehicleInfo) {

		String noOfMiles = additionalVehicleInfo.toString();
		for (int vehicleNumber = 2; vehicleNumber <= 6; vehicleNumber++) {
			validate_whether_user_is_able_to_click_on_yes_and_navigate_to_addtional_vehicle_info_collection_page();

			VehicleInfoPage.selectYear();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			WebElement year = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@data-testid,'vehicle-year-select-listbox')]/li[" + vehicleNumber + "]")));
			year.click();

			// Select Make
			VehicleInfoPage.selectMake();
			WebElement make = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@data-testid,'vehicle-make-select-listbox')]/li[1]")));
			make.click();

			// Select Model
			VehicleInfoPage.selectModel();
			WebElement model = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@data-testid,'vehicle-model-select-listbox')]/li[1]")));
			model.click();

			// Select Trim or Sub model
			VehicleInfoPage.selectTrimOrSubModel();
			WebElement submodel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@data-testid,'vehicle-submodel-select-listbox')]/li[1]")));
			submodel.click();

			// Click on Add
			VehicleInfoPage.clickNextButton();

			// More details of each vehicle
			AdditionalVehicleInfoPage.vehicleOwnershipContainerSelect();
			AdditionalVehicleInfoPage.vehicleOwnershipDropdownValueSelect();
			AdditionalVehicleInfoPage.vehicleUsageContainerSelect();
			AdditionalVehicleInfoPage.vehicleUsageDropdownValueSelect();
			VehicleInfoPage.enterNoOfMiles(noOfMiles);

			// Click on Next
			VehicleInfoPage.clickNextButton();
		}
	}

	@Then("validate whether user is able to enter valid details for Four additional vehicles")
	public void validate_whether_user_is_able_to_enter_valid_details_for_four_additional_vehicles(DataTable additionalVehicleInfo) {

		List<Map<String, String>> additonalvehicleDetails = additionalVehicleInfo.asMaps(String.class, String.class);
		Map<String, String> dataRow = additonalvehicleDetails.get(0);
		String strYear = dataRow.get("Year");
		String strMake = dataRow.get("Make");
		String strModel = dataRow.get("Model");
		String strSubModel = dataRow.get("SubModel");
		String strNoOfMiles = dataRow.get("NoOfMiles");
		for (int vehicleNumber = 1; vehicleNumber <= 4; vehicleNumber++) {
			validate_whether_user_is_able_to_click_on_yes_and_navigate_to_addtional_vehicle_info_collection_page();

			// select Year
			VehicleInfoPage.selectYear();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			WebElement year = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[contains(text(),'" + strYear + "')]")));
			year.click();

			// Select Make
			VehicleInfoPage.selectMake();
			WebElement make = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[contains(text(),'" + strMake + "')]")));
			make.click();

			// Select Model
			VehicleInfoPage.selectModel();
			WebElement model = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[contains(text(),'" + strModel + "')]")));
			model.click();

			// Select Trim or Sub model
			VehicleInfoPage.selectTrimOrSubModel();
			WebElement submodel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[contains(text(),'" + strSubModel + "')]")));
			submodel.click();

			// Click on Add
			VehicleInfoPage.clickNextButton();

			// More details of each vehicle
			AdditionalVehicleInfoPage.vehicleOwnershipContainerSelect();
			AdditionalVehicleInfoPage.vehicleOwnershipDropdownValueSelect();

			AdditionalVehicleInfoPage.vehicleUsageContainerSelect();
			AdditionalVehicleInfoPage.vehicleUsageDropdownValueSelect();

			VehicleInfoPage.enterNoOfMiles(strNoOfMiles);

			// Click on Next
			VehicleInfoPage.clickNextButton();
		}

		Assert.assertTrue(AdditionalVehicleInfoPage.noRadioButton().isEnabled());
		AdditionalVehicleInfoPage.clickNoRadioButton();
		VehicleInfoPage.clickNextButton();
	}
	
	
	@Then("validate whether user is able to enter valid details for Five additional vehicles")
	public void validate_whether_user_is_able_to_enter_valid_details_for_five_additional_vehicles(DataTable additionalVehicleInfo) {

		List<Map<String, String>> additonalvehicleDetails = additionalVehicleInfo.asMaps(String.class, String.class);
		Map<String, String> dataRow = additonalvehicleDetails.get(0);
		String strYear = dataRow.get("Year");
		String strMake = dataRow.get("Make");
		String strModel = dataRow.get("Model");
		String strSubModel = dataRow.get("SubModel");
		String strNoOfMiles = dataRow.get("NoOfMiles");
		for (int vehicleNumber = 1; vehicleNumber <= 5; vehicleNumber++) {
			validate_whether_user_is_able_to_click_on_yes_and_navigate_to_addtional_vehicle_info_collection_page();

			// select Year
			VehicleInfoPage.selectYear();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			WebElement year = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[contains(text(),'" + strYear + "')]")));
			year.click();

			// Select Make
			VehicleInfoPage.selectMake();
			WebElement make = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[contains(text(),'" + strMake + "')]")));
			make.click();

			// Select Model
			VehicleInfoPage.selectModel();
			WebElement model = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[contains(text(),'" + strModel + "')]")));
			model.click();

			// Select Trim or Sub model
			VehicleInfoPage.selectTrimOrSubModel();
			WebElement submodel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[contains(text(),'" + strSubModel + "')]")));
			submodel.click();

			// Click on Add
			VehicleInfoPage.clickNextButton();

			// More details of each vehicle
			AdditionalVehicleInfoPage.vehicleOwnershipContainerSelect();
			AdditionalVehicleInfoPage.vehicleOwnershipDropdownValueSelect();

			AdditionalVehicleInfoPage.vehicleUsageContainerSelect();
			AdditionalVehicleInfoPage.vehicleUsageDropdownValueSelect();

			VehicleInfoPage.enterNoOfMiles(strNoOfMiles);

			// Click on Next
			VehicleInfoPage.clickNextButton();
		}
	}
	
	
//**********************************Quotes page***************************************
	@Then("Click on Next and navigate to Quotes page")
	public void click_on_next_and_navigate_to_quotes_page() {
		QuotesPage.clickNextButton();
		Assert.assertTrue(QuotesPage.preparingQuotes().isDisplayed());
	}
	
	@Then("User must view an error message in Quotes pages")
	public void user_must_view_an_error_message_in_quotes_pages() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(QuotesPage.quotesNotGeneratedText));
		Assert.assertTrue(QuotesPage.quotesNotGenerated().isDisplayed());
	}
	
//**********************Generate Quotes for valid address*****************************
	@Then("Quotes must be generated for valid address")
	public void quotes_must_be_generated_for_valid_address() {
		try {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(QuotesPage.selectAQuoteText));
		Boolean isElmPresent=  driver.findElements(QuotesPage.selectAQuoteText).size() > 0;
		if (isElmPresent == true) {
		Assert.assertTrue(QuotesPage.selectAQuote().isDisplayed());
		}
		}catch(Exception e){
		    e.printStackTrace();
	}
	}
	
//*******************************Add 5 Additional Drivers*****************************

	@Then("validate whether user is able to click on Yes and navigate to addtional driver info collection page")
	public void validate_whether_user_is_able_to_click_on_yes_and_navigate_to_addtional_driver_info_collection_page() {
		
			Assert.assertTrue(AdditionalDriverInfoPage.addAnotherDriverRadioButton().isEnabled());
			AdditionalDriverInfoPage.clickAddAnotherDriverRadioButton();
			WebElement NextButton = driver.findElement(By.xpath("//*[contains(@data-testid,'next-btn')]"));
			NextButton.click();
			
	}
	
	@Then("validate whether user is able to enter valid details in Additional driver page")
	public void validate_whether_user_is_able_to_enter_valid_details_in_additional_driver_page(DataTable additionalDriverInfo) {
 
		for (Map<String, String> data : additionalDriverInfo.asMaps(String.class, String.class)) {	
			validate_whether_user_is_able_to_click_on_yes_and_navigate_to_addtional_driver_info_collection_page();
			AdditionalDriverInfoPage.enterDriverFirstName(data.get("FirstName"));
			AdditionalDriverInfoPage.enterDriverLastName(data.get("LastName"));
			AdditionalDriverInfoPage.enterDriverDOBMonth(data.get("Month"));
			AdditionalDriverInfoPage.enterDriverDOBDay(data.get("Day"));
			AdditionalDriverInfoPage.enterDriverDOBYear(data.get("Year"));
			AdditionalDriverInfoPage.clickGenderDropdown();
			AdditionalDriverInfoPage.clickGenderValue();
			AdditionalDriverInfoPage.clickAddDriverButton();
			}
	}
		
	@Then("validate whether user is able click on No and navigate to GarageInfo page")
	public void validate_whether_user_is_able_click_on_No_and_navigate_to_GarageInfo_page() {
		AdditionalDriverInfoPage.clickDoNotAddAnotherDriverRadioButton();
		WebElement NextButton = driver.findElement(By.xpath("//*[contains(@data-testid,'next-btn')]"));
		NextButton.click();

	}
	
//******************************Close the Browser********************************
	@And("close the chrome browser")
	public void close_the_chrome_browser() {
		System.out.println("Closing the browser");
		driver.quit();
	}
	
}
