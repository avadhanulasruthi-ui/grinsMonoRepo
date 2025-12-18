package elementLocators.omsite.ho3;

import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PersonalInfoPage {

	public static WebDriver driver;
	public static Properties prop;

	public PersonalInfoPage(WebDriver driver) {
		this.driver = driver;
	}

	public static By firstName = By.xpath("//input[contains(@data-testid,'first-name-input')]");
	public static By lastName = By.xpath("//input[contains(@data-testid,'last-name-input')]");
	public static By month = By.xpath("//input[contains(@data-testid,'month-input')]");
	public static By day = By.xpath("//input[contains(@data-testid,'day-input')]");
	public static By year = By.xpath("//input[contains(@data-testid,'year-input')]");
	public static By genderDropdown = By.xpath("//*[contains(text(),'Gender')]");
	public static By gender = By.xpath("//ul/li[contains(text(),'Male')]");
	public static By maritalStatusDropdown = By.xpath("//*[contains(text(),'Marital status')]");
	public static By personalMaritalStatus = By.xpath("//ul/li[contains(text(),'Single')]");
	public static By phoneNumber = By.xpath("//input[contains(@data-testid,'phone-number-input')]");
	public static By nextButton = By.xpath("//button[@data-testid='next-btn']");
	public static By disablednextButton = By.xpath("//button[@disabled and @data-testid='next-btn']");
	public static By personalInfoNextPageText = By.xpath("//*[contains(text(),'Want to add anyone to the quote?')]");
	public static By noRadioButton = By.xpath("//input[@data-testid='add-co-applicant-no-radio-btn']");
	public static By errorMessage = By.xpath("//div[@data-testid='help-text']");
	public static By cookieAlert = By.xpath("//*[contains(@id,'onetrust-group-container')]");
	public static By closeCookieAlert = By.xpath("//*[contains(@id,'onetrust-close-btn-container')]");

	public static void enterFirstName(String driverFirstName) {
		driver.findElement(firstName).sendKeys(driverFirstName);
	}

	public static void enterLastName(String driverLastName) {
		driver.findElement(lastName).sendKeys(driverLastName);
	}

	public static void enterDOBMonth(String dobMonth) {
		driver.findElement(month).sendKeys(dobMonth);
	}

	public static void enterDOBDay(String dobDay) {
		driver.findElement(day).sendKeys(dobDay);
	}

	public static void enterDOBYear(String dobYear) {
		driver.findElement(year).sendKeys(dobYear);
	}

	public static void genderDropdown() {
		driver.findElement(genderDropdown).click();
	}

	public static void selectGender() {
		driver.findElement(gender).click();
	}

	public static void maritalStatusDropdown() {
		driver.findElement(maritalStatusDropdown).click();
	}

	public static void personalMaritalStatus() {
		driver.findElement(personalMaritalStatus).click();
	}

	public static WebElement nextButton() {
		return driver.findElement(nextButton);
	}
	
	public static WebElement personalInfoNextPageText() {
		return driver.findElement(personalInfoNextPageText);
	}
	
	public static WebElement noRadioButton() {
		return driver.findElement(noRadioButton);
	}
	
	public static WebElement errorMessage() {
		return driver.findElement(errorMessage);
	}
	
	public static WebElement disablednextButton() {
		return driver.findElement(disablednextButton);
	}
	
	public static void enterPhoneNumber(String personalPhoneNumber) {
		driver.findElement(phoneNumber).sendKeys(personalPhoneNumber);
	}

	public static void cookieAlert() {
		driver.findElement(cookieAlert).click();
	}

	public static void closeCookieAlert() {
		driver.findElement(closeCookieAlert).click();
	}
	
}