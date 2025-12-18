package stepDefinition.omsite.ho3;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.net.SocketException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import elementLocators.omsite.ho3.PersonalInfoPage;
import elementLocators.omsite.ho3.AddressInfoPage;
import elementLocators.omsite.auto.GarageInfoPage;
import elementLocators.omsite.ho3.AddingCoinsurerPage;
import utils.TestInitialize;

public class HO3StepDef {
	public static WebDriver driver;
	public static Properties prop;

	public HO3StepDef() {
		prop = TestInitialize.getProperties();
	}

	public static void initialization() {
		String browsername = prop.getProperty("browser");
		if (browsername.equals("chrome")) {
			driver = TestInitialize.getChromeDriver(prop);
		}
		PersonalInfoPage personalInfoPage = new PersonalInfoPage(driver);
		AddressInfoPage addressInfoPage = new AddressInfoPage(driver);
		AddingCoinsurerPage addingCoinsurerPage = new AddingCoinsurerPage(driver);
	}

	@Given("Open the browser")
	public void Open_the_browser() {
		try {
			initialization();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@And("launch the url")
	public void launch_the_url() {
		driver.get(prop.getProperty("url"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(PersonalInfoPage.cookieAlert));
		Boolean isCookieAlertPresent =  driver.findElements(PersonalInfoPage.cookieAlert).size() > 0;
		if(isCookieAlertPresent == true)
		{
			PersonalInfoPage.closeCookieAlert();
		}
	}

	@Then("validate whether user is on the right page")
	public void validate_whether_user_is_on_the_right_page() {
		System.out.println("Title of the page is: " + driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains("Guaranteed Rate"));

	}

	@Then("validate whether user is able to enter valid user data")
	public void validate_whether_user_is_able_to_enter_valid_user_data(DataTable PersonalInfo) {
		try {
			System.out.println("Title of the page is: " + driver.getTitle());
			List<Map<String, String>> personalInfoData = PersonalInfo.asMaps(String.class, String.class);
			Map<String, String> dataRow = personalInfoData.get(0);
			PersonalInfoPage.enterFirstName(dataRow.get("FirstName"));
			PersonalInfoPage.enterLastName(dataRow.get("LastName"));
			PersonalInfoPage.enterDOBMonth(dataRow.get("Month"));
			PersonalInfoPage.enterDOBDay(dataRow.get("Day"));
			PersonalInfoPage.enterDOBYear(dataRow.get("Year"));
			PersonalInfoPage.maritalStatusDropdown();
			PersonalInfoPage.personalMaritalStatus();
			PersonalInfoPage.enterPhoneNumber(dataRow.get("PhoneNumber"));
			Thread.sleep(2000);
			WebElement Nextbutton = driver.findElement(PersonalInfoPage.nextButton);
			Assert.assertTrue(Nextbutton.isEnabled());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Then("validate whether user is able to navigate to next page")
	public void validate_whether_user_is_able_to_navigate_to_next_page() {
		try {
			WebElement Nextbutton = PersonalInfoPage.nextButton();
			Assert.assertTrue(Nextbutton.isEnabled());
			Nextbutton.click();
			Thread.sleep(5000);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfElementLocated(PersonalInfoPage.personalInfoNextPageText));
			Assert.assertTrue(PersonalInfoPage.personalInfoNextPageText().isDisplayed());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

	}

	@Then("validate whether user is on add additional insurer page")
	public void validate_whether_user_is_on_add_additional_insurer_page() {
		try {
			WebElement NoCheckBox = PersonalInfoPage.noRadioButton();
			Assert.assertTrue(NoCheckBox.isEnabled());
			NoCheckBox.click();
			Thread.sleep(2000);
			WebElement NextButton = PersonalInfoPage.nextButton();
			NextButton.click();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Then("validate whether user is able to provide property address")
	public void validate_whether_user_is_able_to_provide_property_address(DataTable PropertyAddress) {
		try {
			List<Map<String, String>> propertyAddressData = PropertyAddress.asMaps(String.class, String.class);
			Map<String, String> dataRow = propertyAddressData.get(0);

			Actions actions = new Actions(driver);
			String strAddress = dataRow.get("Address");
			WebElement address = AddressInfoPage.addressText();

			AddressInfoPage.enterDataAction(address, strAddress);
			AddressInfoPage.clickDropDownAction(AddressInfoPage.addressListDropdown());

			WebElement month = AddressInfoPage.enterDOBMonth();
			AddressInfoPage.enterDataAction(month, dataRow.get("Month"));
			Assert.assertTrue(month.isEnabled());

			WebElement year = AddressInfoPage.enterDOBYear();
			AddressInfoPage.enterDataAction(year, dataRow.get("Year"));
			Assert.assertTrue(year.isEnabled());

			WebElement NextButton = AddressInfoPage.nextButton();
			NextButton.click();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@When("user enters invalid date")
	public void user_enters_invalid_date(DataTable InvalidPersonalInfo) {
		try {
			List<Map<String, String>> invalidPersonalInfoData = InvalidPersonalInfo.asMaps(String.class, String.class);
			Map<String, String> dataRow = invalidPersonalInfoData.get(0);
			PersonalInfoPage.enterFirstName(dataRow.get("FirstName"));
			PersonalInfoPage.enterLastName(dataRow.get("LastName"));
			PersonalInfoPage.enterDOBMonth(dataRow.get("Month"));
			PersonalInfoPage.enterDOBDay(dataRow.get("Day"));
			PersonalInfoPage.enterDOBYear(dataRow.get("Year"));
			PersonalInfoPage.maritalStatusDropdown();
			PersonalInfoPage.personalMaritalStatus();
			PersonalInfoPage.enterPhoneNumber(dataRow.get("PhoneNumber"));
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Then("validate whether user able to view the error message for invalid date of birth")
	public void validate_whether_user_able_to_view_the_error_message_for_invalid_date_of_birth() {
		try {
			Thread.sleep(4000);
			WebElement invalidDateErrorMessage = PersonalInfoPage.errorMessage();
			Assert.assertTrue(invalidDateErrorMessage.isDisplayed());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@And("validate whether the Next button is disabled")
	public void validate_whether_the_Next_button_is_disabled() {
		try {
			Thread.sleep(4000);
			WebElement disabledNextButton = PersonalInfoPage.disablednextButton();
			Assert.assertTrue(disabledNextButton.isDisplayed());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@And("validate whether the Add button is disabled")
	public void validate_whether_the_Add_button_is_disabled() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddingCoinsurerPage.disablednextButton));
		WebElement disabledAddButton = AddingCoinsurerPage.disablednextButton();
		Assert.assertTrue(disabledAddButton.isDisplayed());
	}

	@When("user enters invalid day")
	public void user_enters_invalid_day(DataTable InvalidDayInPersonalInfo) {
		try {
			List<Map<String, String>> personalInfoData = InvalidDayInPersonalInfo.asMaps(String.class, String.class);
			Map<String, String> dataRow = personalInfoData.get(0);
			PersonalInfoPage.enterFirstName(dataRow.get("FirstName"));
			PersonalInfoPage.enterLastName(dataRow.get("LastName"));
			PersonalInfoPage.enterDOBMonth(dataRow.get("Month"));
			PersonalInfoPage.enterDOBDay(dataRow.get("Day"));
			PersonalInfoPage.enterDOBYear(dataRow.get("Year"));
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@When("user enters invalid month")
	public void user_enters_invalid_month(DataTable InvalidMonthInPersonalInfo) {
		try {
			List<Map<String, String>> invalidMonthPersonalInfoData = InvalidMonthInPersonalInfo.asMaps(String.class,
					String.class);
			Map<String, String> dataRow = invalidMonthPersonalInfoData.get(0);
			PersonalInfoPage.enterFirstName(dataRow.get("FirstName"));
			PersonalInfoPage.enterLastName(dataRow.get("LastName"));
			PersonalInfoPage.enterDOBMonth(dataRow.get("Month"));
			PersonalInfoPage.enterDOBDay(dataRow.get("Day"));
			PersonalInfoPage.enterDOBYear(dataRow.get("Year"));
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@When("user enters invalid year")
	public void user_enters_invalid_year(DataTable InvalidYearInPersonalInfo) {
		try {
			List<Map<String, String>> invalidYearPersonalInfoData = InvalidYearInPersonalInfo.asMaps(String.class,
					String.class);
			Map<String, String> dataRow = invalidYearPersonalInfoData.get(0);
			PersonalInfoPage.enterFirstName(dataRow.get("FirstName"));
			PersonalInfoPage.enterLastName(dataRow.get("LastName"));
			PersonalInfoPage.enterDOBMonth(dataRow.get("Month"));
			PersonalInfoPage.enterDOBDay(dataRow.get("Day"));
			PersonalInfoPage.enterDOBYear(dataRow.get("Year"));
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@When("user enters invalid phone number")
	public void user_enters_invalid_phone_number(DataTable InvalidPhoneNumberInPersonalInfo) {
		try {
			List<Map<String, String>> invalidPhoneNumberPersonalInfoData = InvalidPhoneNumberInPersonalInfo
					.asMaps(String.class, String.class);
			Map<String, String> dataRow = invalidPhoneNumberPersonalInfoData.get(0);
			PersonalInfoPage.enterFirstName(dataRow.get("FirstName"));
			PersonalInfoPage.enterLastName(dataRow.get("LastName"));
			PersonalInfoPage.enterDOBMonth(dataRow.get("Month"));
			PersonalInfoPage.enterDOBDay(dataRow.get("Day"));
			PersonalInfoPage.enterDOBYear(dataRow.get("Year"));
			PersonalInfoPage.maritalStatusDropdown();
			PersonalInfoPage.personalMaritalStatus();
			PersonalInfoPage.enterPhoneNumber(dataRow.get("PhoneNumber"));
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Then("validate the marital status data")
	public void validate_the_marital_status_data(DataTable PersonalInfoMaritalStatus) {
		try {
			List<Map<String, String>> personalInfoDataMaritalStatus = PersonalInfoMaritalStatus.asMaps(String.class,
					String.class);
			Map<String, String> dataRow = personalInfoDataMaritalStatus.get(0);
			System.out.println("Title of the page is: " + driver.getTitle());
			PersonalInfoPage.enterFirstName(dataRow.get("FirstName"));
			PersonalInfoPage.enterLastName(dataRow.get("LastName"));
			PersonalInfoPage.enterDOBMonth(dataRow.get("Month"));
			PersonalInfoPage.enterDOBDay(dataRow.get("Day"));
			PersonalInfoPage.enterDOBYear(dataRow.get("Year"));
			PersonalInfoPage.enterPhoneNumber(dataRow.get("PhoneNumber"));
			PersonalInfoPage.maritalStatusDropdown();
			PersonalInfoPage.personalMaritalStatus();
			Thread.sleep(2000);
			WebElement Nextbutton = driver.findElement(PersonalInfoPage.nextButton);
			Assert.assertTrue(Nextbutton.isEnabled());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Then("validate the error messgae for invalid purchase date")
	public void validate_the_error_messgae_for_invalid_purchase_date(DataTable PropertyAddress) {
		try {
			List<Map<String, String>> propertyAddressData = PropertyAddress.asMaps(String.class, String.class);
			Map<String, String> dataRow = propertyAddressData.get(0);

			Actions actions = new Actions(driver);
			String strAddress = dataRow.get("Address");
			WebElement address = AddressInfoPage.addressText();

			AddressInfoPage.enterDataAction(address, strAddress);
			AddressInfoPage.clickDropDownAction(AddressInfoPage.addressListDropdown());

			WebElement month = AddressInfoPage.enterDOBMonth();
			AddressInfoPage.enterDataAction(month, dataRow.get("Month"));
			Assert.assertTrue(month.isEnabled());

			WebElement year = AddressInfoPage.enterDOBYear();
			AddressInfoPage.enterDataAction(year, dataRow.get("Year"));
			Assert.assertTrue(year.isEnabled());

			Thread.sleep(4000);
			Assert.assertTrue(AddressInfoPage.errorMessage().isDisplayed());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Then("validate the error messgae for invalid property address")
	public void validate_the_error_messgae_for_invalid_property_address(DataTable PropertyAddress) {
		try {
			List<Map<String, String>> propertyAddressData = PropertyAddress.asMaps(String.class, String.class);
			Map<String, String> dataRow = propertyAddressData.get(0);
			AddressInfoPage.addressText().sendKeys(dataRow.get("Address"));
			AddressInfoPage.aptSuiteNumber().sendKeys(dataRow.get("Apartment"));
			AddressInfoPage.city().sendKeys(dataRow.get("City"));
			AddressInfoPage.state().click();
			AddressInfoPage.stateDropdownValue().click();
			AddressInfoPage.zipCodeText().sendKeys(dataRow.get("Zip"));
			AddressInfoPage.enterDOBMonth().sendKeys(dataRow.get("Month"));
			AddressInfoPage.enterDOBYear().sendKeys(dataRow.get("Year"));
			Thread.sleep(4000);
			WebElement disabledNextButton = AddressInfoPage.disablednextButton();
			Assert.assertTrue(disabledNextButton.isDisplayed());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Then("validate the error message on quotes screen")
	public void validate_the_error_message_on_quotes_screen(DataTable PropertyAddress) throws SocketException {
		List<Map<String, String>> propertyAddressData = PropertyAddress.asMaps(String.class, String.class);
		Map<String, String> dataRow = propertyAddressData.get(0);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		AddressInfoPage.addressText().sendKeys(dataRow.get("Address"));
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.TAB).sendKeys(Keys.TAB).build().perform();
		AddressInfoPage.city().sendKeys(dataRow.get("City"));
		AddressInfoPage.state().click();
		AddressInfoPage.selectStateTexas();
		AddressInfoPage.zipCodeText().sendKeys(dataRow.get("ZipCode"));
		AddressInfoPage.enterDOBMonth().sendKeys(dataRow.get("Month"));
		AddressInfoPage.enterDOBYear().sendKeys(dataRow.get("Year"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddressInfoPage.nextButton));
		WebElement Nextbutton = AddressInfoPage.nextButton();
		Assert.assertTrue(Nextbutton.isEnabled());
		Nextbutton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddressInfoPage.errorMessageText));
		Assert.assertTrue(AddressInfoPage.errorMessageText().isDisplayed());
	}

	@Then("validate whether user is able to add additional insurer")
	public void validate_whether_user_is_able_to_add_additional_insurer(DataTable CoinsurerInfo) {
		List<Map<String, String>> coinsurerInfoData = CoinsurerInfo.asMaps(String.class, String.class);
		Map<String, String> dataRow = coinsurerInfoData.get(0);

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfElementLocated(AddingCoinsurerPage.yesRadioButton));
			WebElement yesCheckBox = AddingCoinsurerPage.yesRadioButton();
			Assert.assertTrue(AddingCoinsurerPage.noRadioButton().isEnabled());
			Assert.assertTrue(yesCheckBox.isEnabled());
			yesCheckBox.click();
			WebElement Nextbutton = AddingCoinsurerPage.nextButton();
			Assert.assertTrue(Nextbutton.isEnabled());
			Nextbutton.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(AddingCoinsurerPage.firstName));
			AddingCoinsurerPage.enterFirstName(dataRow.get("FirstName"));
			AddingCoinsurerPage.enterLastName(dataRow.get("LastName"));
			AddingCoinsurerPage.enterDOBMonth(dataRow.get("Month"));
			AddingCoinsurerPage.enterDOBDay(dataRow.get("Day"));
			AddingCoinsurerPage.enterDOBYear(dataRow.get("Year"));
			AddingCoinsurerPage.selectMaritalStatus();
			AddingCoinsurerPage.selectPersonalMaritalStatus();
			WebElement Addbutton = AddingCoinsurerPage.nextButton();
			Assert.assertTrue(Addbutton.isEnabled());
			Addbutton.click();
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Then("validate the error message when user enter invalid date of birth for the coinsurer")
	public void validate_the_error_message_when_user_enter_invalid_date_of_birth_for_the_coinsurer(
			DataTable CoinsurerInfo) {
		List<Map<String, String>> coinsurerInfoData = CoinsurerInfo.asMaps(String.class, String.class);
		Map<String, String> dataRow = coinsurerInfoData.get(0);
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfElementLocated(AddingCoinsurerPage.yesRadioButton));
			WebElement yesCheckBox = AddingCoinsurerPage.yesRadioButton();
			Assert.assertTrue(AddingCoinsurerPage.noRadioButton().isEnabled());
			Assert.assertTrue(yesCheckBox.isEnabled());
			yesCheckBox.click();
			Thread.sleep(2000);
			WebElement Nextbutton = AddingCoinsurerPage.nextButton();
			Assert.assertTrue(Nextbutton.isEnabled());
			Nextbutton.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(AddingCoinsurerPage.firstName));
			AddingCoinsurerPage.enterFirstName(dataRow.get("FirstName"));
			AddingCoinsurerPage.enterLastName(dataRow.get("LastName"));
			AddingCoinsurerPage.enterDOBMonth(dataRow.get("Month"));
			AddingCoinsurerPage.enterDOBDay(dataRow.get("Day"));
			AddingCoinsurerPage.enterDOBYear(dataRow.get("Year"));
			WebElement invalidDateErrorMessage = AddingCoinsurerPage.errorMessage();
			Assert.assertTrue(invalidDateErrorMessage.isDisplayed());
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Then("validate whether user is clicking on yes button")
	public void validate_whether_user_is_clicking_on_yes_button() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfElementLocated(AddingCoinsurerPage.yesRadioButton));
			WebElement yesCheckBox = AddingCoinsurerPage.yesRadioButton();
			Assert.assertTrue(AddingCoinsurerPage.noRadioButton().isEnabled());
			Assert.assertTrue(yesCheckBox.isEnabled());
			yesCheckBox.click();
			Thread.sleep(2000);
			WebElement Nextbutton = AddingCoinsurerPage.nextButton();
			Assert.assertTrue(Nextbutton.isEnabled());
			Nextbutton.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(AddingCoinsurerPage.firstName));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Then("validate whether user is able to navigate to quotes page")
	public void validate_whether_user_is_able_to_navigate_to_quotes_page() {
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60))
					.pollingEvery(Duration.ofSeconds(10)).ignoring(TimeoutException.class);
			wait.until(ExpectedConditions.visibilityOfElementLocated(AddingCoinsurerPage.quotesText));
			Boolean isElmPresent = driver.findElements(AddingCoinsurerPage.quotesText).size() > 0;
			if (isElmPresent == true) {
				Assert.assertTrue(AddingCoinsurerPage.quotesText().isDisplayed());
				Assert.assertTrue(AddingCoinsurerPage.selectQuoteText().isDisplayed());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@And("close the browser")
	public void close_the_browser() {
		System.out.println("Closing the browser");
		driver.quit();
	}

	@Given("Open the Firefox and launch the application")
	public void open_the_firefox_and_launch_the_application() {
		// Write code here that turns the phrase above into concrete actions
	}
	 
}
