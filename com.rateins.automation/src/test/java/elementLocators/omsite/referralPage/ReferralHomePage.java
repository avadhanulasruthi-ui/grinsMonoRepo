package elementLocators.omsite.referralPage;

import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReferralHomePage {

	public static WebDriver driver;
	public static Properties prop;

	public ReferralHomePage(WebDriver driver) {
		this.driver = driver;
	}

	public static By firstName = By.xpath("//input[contains(@data-testid,'first-name-input')]");
	public static By lastName = By.xpath("//input[contains(@data-testid,'last-name-input')]");
	public static By phoneNumber = By.xpath("//input[contains(@data-testid,'phone-number-input')]");
	public static By emailAddress = By.xpath("//input[contains(@data-testid,'email-input')]");
	public static By nextButton = By.xpath("//button[@data-testid='next-btn']");
	public static By referralHomeNextPageText = By.xpath("//*[contains(text(),'Thank you')]");
	public static By referralHomeSupportText1 = By.xpath("//*[contains(text(),'By entering your email address and submitting this form')]");
	public static By referralHomeSupportText2 = By.xpath("//*[contains(text(),'Want to talk? Give me a call')]");
	public static By cookieAlert = By.xpath("//*[contains(@id,'onetrust-group-container')]");
	public static By closeCookieAlert = By.xpath("//*[contains(@id,'onetrust-close-btn-container')]");

	public static void enterFirstName(String driverFirstName) {
		driver.findElement(firstName).sendKeys(driverFirstName);
	}

	public static void enterLastName(String driverLastName) {
		driver.findElement(lastName).sendKeys(driverLastName);
	}
	
	public static void enterPhoneNumber(String personalPhoneNumber) {
		driver.findElement(phoneNumber).sendKeys(personalPhoneNumber);
	}
	
	public static void enterEmailAddress(String personalEmailAddress) {
		driver.findElement(emailAddress).sendKeys(personalEmailAddress);
	}
	
	public static WebElement nextButton() {
		return driver.findElement(nextButton);
	}
	
	public static WebElement referralHomeNextPageText() {
		return driver.findElement(referralHomeNextPageText);
	}	
	
	public static void cookieAlert() {
		driver.findElement(cookieAlert).click();
	}

	public static void closeCookieAlert() {
		driver.findElement(closeCookieAlert).click();
	}
	
}