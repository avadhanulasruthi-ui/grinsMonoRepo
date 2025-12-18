package stepDefinition.omsite.referralPage;

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
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import elementLocators.omsite.ho3.PersonalInfoPage;
import elementLocators.omsite.referralPage.ReferralHomePage;
import utils.TestInitialize;
import org.openqa.selenium.JavascriptExecutor;

public class ReferralPageStepDef {
	public static WebDriver driver;
	public static Properties prop;

	public ReferralPageStepDef() {
		prop = TestInitialize.getProperties();
	}

	public static void initialization() {
		String browsername = prop.getProperty("browser");
		if (browsername.equals("chrome")) {
			driver = TestInitialize.getChromeDriver(prop);
		}
		ReferralHomePage referralHomePage = new ReferralHomePage(driver);
	}

	@Given("launch the chrome browser")
	public void launch_the_chrome_browser() {
		try {
			initialization();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@And("launch the referral home page")
	public void launch_the_referral_home_page() {
		driver.get("https://quote-qa.nonprod.rateins.com/referrals/agents/nick-brown");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ReferralHomePage.cookieAlert));
		Boolean isCookieAlertPresent =  driver.findElements(ReferralHomePage.cookieAlert).size() > 0;
		if(isCookieAlertPresent == true)
		{
			ReferralHomePage.closeCookieAlert();
		}
	}

	@Then("validate whether user is on the referral home page")
	public void validate_whether_user_is_on_the_referral_home_page() {
		System.out.println("Title of the page is: " + driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains("Guaranteed Rate"));
	}
	
	@And("close chrome browser")
	public void close_chrome_browser() {
		System.out.println("Closing the browser");
		driver.quit();
	}

	@Then("validate whether user is able to enter valid data on referral home page")
	public void validate_whether_user_is_able_to_enter_valid_data_on_referral_home_page(DataTable ReferralHomePageInfo) {
		try {
			System.out.println("Title of the page is: " + driver.getTitle());
			List<Map<String, String>> referralHomePageInfoData = ReferralHomePageInfo.asMaps(String.class, String.class);
			Map<String, String> dataRow = referralHomePageInfoData.get(0);
			ReferralHomePage.enterFirstName(dataRow.get("FirstName"));
			ReferralHomePage.enterLastName(dataRow.get("LastName"));
			ReferralHomePage.enterPhoneNumber(dataRow.get("PhoneNumber"));
			ReferralHomePage.enterEmailAddress(dataRow.get("EmailAddress"));
			Thread.sleep(2000);
			WebElement Nextbutton = driver.findElement(ReferralHomePage.nextButton);
			Assert.assertTrue(Nextbutton.isEnabled());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Then("validate whether user is able to navigate to next page from Referral Home Page")
	public void validate_whether_user_is_able_to_navigate_to_next_page_from_referral_home_page(DataTable ReferralHomePageInfo) {
		try {
			System.out.println("Title of the page is: " + driver.getTitle());
			List<Map<String, String>> referralHomePageInfoData = ReferralHomePageInfo.asMaps(String.class, String.class);
			Map<String, String> dataRow = referralHomePageInfoData.get(0);
			ReferralHomePage.enterFirstName(dataRow.get("FirstName"));
			ReferralHomePage.enterLastName(dataRow.get("LastName"));
			ReferralHomePage.enterPhoneNumber(dataRow.get("PhoneNumber"));
			ReferralHomePage.enterEmailAddress(dataRow.get("EmailAddress"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			Thread.sleep(2000);
			WebElement Nextbutton = driver.findElement(ReferralHomePage.nextButton);
			Assert.assertTrue(Nextbutton.isEnabled());
			Nextbutton.click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfElementLocated(ReferralHomePage.referralHomeNextPageText));
			Assert.assertTrue(ReferralHomePage.referralHomeNextPageText().isDisplayed());		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Then("validate whether user is able to navigate to next page when user enters invalid phone number")
	public void validate_whether_user_is_able_to_navigate_to_next_page_when_user_enters_invalid_phone_number(DataTable ReferralHomePageInfo) {
		try {
			System.out.println("Title of the page is: " + driver.getTitle());
			List<Map<String, String>> referralHomePageInfoData = ReferralHomePageInfo.asMaps(String.class, String.class);
			Map<String, String> dataRow = referralHomePageInfoData.get(0);
			ReferralHomePage.enterFirstName(dataRow.get("FirstName"));
			ReferralHomePage.enterLastName(dataRow.get("LastName"));
			ReferralHomePage.enterPhoneNumber(dataRow.get("PhoneNumber"));
			ReferralHomePage.enterEmailAddress(dataRow.get("EmailAddress"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			Thread.sleep(2000);
			WebElement Nextbutton = driver.findElement(ReferralHomePage.nextButton);
			Assert.assertFalse(Nextbutton.isEnabled());		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Then("validate whether user is able to navigate to next page when user enters invalid first name")
	public void validate_whether_user_is_able_to_navigate_to_next_page_when_user_enters_invalid_first_name(DataTable ReferralHomePageInfo) {
		try {
			System.out.println("Title of the page is: " + driver.getTitle());
			List<Map<String, String>> referralHomePageInfoData = ReferralHomePageInfo.asMaps(String.class, String.class);
			Map<String, String> dataRow = referralHomePageInfoData.get(0);
			ReferralHomePage.enterLastName(dataRow.get("LastName"));
			ReferralHomePage.enterPhoneNumber(dataRow.get("PhoneNumber"));
			ReferralHomePage.enterEmailAddress(dataRow.get("EmailAddress"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			Thread.sleep(2000);
			WebElement Nextbutton = driver.findElement(ReferralHomePage.nextButton);
			Assert.assertFalse(Nextbutton.isEnabled());		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Then("validate whether user is able to navigate to next page when user enters invalid last name")
	public void validate_whether_user_is_able_to_navigate_to_next_page_when_user_enters_invalid_last_name(DataTable ReferralHomePageInfo) {
		try {
			System.out.println("Title of the page is: " + driver.getTitle());
			List<Map<String, String>> referralHomePageInfoData = ReferralHomePageInfo.asMaps(String.class, String.class);
			Map<String, String> dataRow = referralHomePageInfoData.get(0);
			ReferralHomePage.enterFirstName(dataRow.get("FirstName"));
			ReferralHomePage.enterPhoneNumber(dataRow.get("PhoneNumber"));
			ReferralHomePage.enterEmailAddress(dataRow.get("EmailAddress"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			Thread.sleep(2000);
			WebElement Nextbutton = driver.findElement(ReferralHomePage.nextButton);
			Assert.assertFalse(Nextbutton.isEnabled());		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Then("validate whether user is able to navigate to next page when user enters invalid email address")
	public void validate_whether_user_is_able_to_navigate_to_next_page_when_user_enters_invalid_email_address(DataTable ReferralHomePageInfo) {
		try {
			System.out.println("Title of the page is: " + driver.getTitle());
			List<Map<String, String>> referralHomePageInfoData = ReferralHomePageInfo.asMaps(String.class, String.class);
			Map<String, String> dataRow = referralHomePageInfoData.get(0);
			ReferralHomePage.enterFirstName(dataRow.get("FirstName"));
			ReferralHomePage.enterLastName(dataRow.get("LastName"));
			ReferralHomePage.enterPhoneNumber(dataRow.get("PhoneNumber"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			Thread.sleep(5000);
			WebElement Nextbutton = driver.findElement(ReferralHomePage.nextButton);
			Assert.assertTrue(Nextbutton.isEnabled());	
			Nextbutton.click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfElementLocated(ReferralHomePage.referralHomeNextPageText));
			Assert.assertTrue(ReferralHomePage.referralHomeNextPageText().isDisplayed());	
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Then("validate all the elements on referral home page and page title")
	public void referral_home_page_validation() {
		System.out.println("Title of the page is: " + driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains("Guaranteed Rate"));
		Assert.assertTrue(driver.findElement(ReferralHomePage.firstName).isDisplayed());
		Assert.assertTrue(driver.findElement(ReferralHomePage.lastName).isDisplayed());
		Assert.assertTrue(driver.findElement(ReferralHomePage.phoneNumber).isDisplayed());
		Assert.assertTrue(driver.findElement(ReferralHomePage.emailAddress).isDisplayed());
	}
	
	@Then("validate all the Support details on referral home page")
	public void referral_home_page_support_details_validation() {
		try {
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			Assert.assertTrue(driver.findElement(ReferralHomePage.referralHomeSupportText1).isDisplayed());
			Assert.assertTrue(driver.findElement(ReferralHomePage.referralHomeSupportText2).isDisplayed());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
