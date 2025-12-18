package elementLocators.omsite.auto;

import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DriverInfoPage {

	public static WebDriver driver;
	public static Properties prop;

	public DriverInfoPage(WebDriver driver) {
		this.driver = driver;
	}

	public static By firstName = By.xpath("//input[contains(@data-testid,'first-name-input')]");
	public static By lastName = By.xpath("//input[contains(@data-testid,'last-name-input')]");
	public static By month = By.xpath("//input[contains(@data-testid,'month-input')]");
	public static By day = By.xpath("//input[contains(@data-testid,'day-input')]");
	public static By year = By.xpath("//input[contains(@data-testid,'year-input')]");
	public static By genderDropdown = By.xpath("//*[contains(text(),'Gender')]");
	public static By driverGender = By.xpath("//ul/li[contains(text(),'Male')]");
	public static By maritalStatusDropdown = By.xpath("//*[contains(text(),'Marital status')]");
	public static By driverMaritalStatus = By.xpath("//ul/li[contains(text(),'Single')]");
	public static By phoneNumber = By.xpath("//input[contains(@data-testid,'phone-number-input')]");
	public static By emailAddress = By.xpath("//input[contains(@data-testid,'email-input')]");
	public static By cookieAlert = By.xpath("//*[contains(@id,'onetrust-group-container')]");
	public static By closeCookieAlert = By.xpath("//*[contains(@id,'onetrust-close-btn-container')]");
	
	public static void enterDriverFirstName(String driverFirstName) {
		driver.findElement(firstName).sendKeys(driverFirstName);
	}

	public static void enterDriverLastName(String driverLastName) {
		driver.findElement(lastName).sendKeys(driverLastName);
	}

	public static void enterDriverDOBMonth(String dobMonth) {
		driver.findElement(month).sendKeys(dobMonth);
	}

	public static void enterDriverDOBDay(String dobDay) {
		driver.findElement(day).sendKeys(dobDay);
	}

	public static void enterDriverDOBYear(String dobYear) {
		driver.findElement(year).sendKeys(dobYear);
	}

	public static void genderDropdown() {
		driver.findElement(genderDropdown).click();
	}

	public static void selectGender() {
		driver.findElement(driverGender).click();
	}

	public static void maritalStatusDropdown() {
		driver.findElement(maritalStatusDropdown).click();
	}

	public static void driverMaritalStatus() {
		driver.findElement(driverMaritalStatus).click();
	}

	public static void enterPhoneNumber(String driverPhoneNumber) {
		driver.findElement(phoneNumber).sendKeys(driverPhoneNumber);
	}
	
	public static void enterEmailAddress(String driverEmailAddress) {
		driver.findElement(emailAddress).sendKeys(driverEmailAddress);
	}
	
	public static void cookieAlert() {
		driver.findElement(cookieAlert).click();
	}
	
	public static void closeCookieAlert() {
		driver.findElement(closeCookieAlert).click();
	}

}