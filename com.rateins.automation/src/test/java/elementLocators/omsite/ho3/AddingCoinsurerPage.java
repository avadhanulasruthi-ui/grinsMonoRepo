package elementLocators.omsite.ho3;

import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddingCoinsurerPage {

	public static WebDriver driver;
	public static Properties prop;

	public AddingCoinsurerPage(WebDriver driver) {
		this.driver = driver;
	}

	public static By firstName = By.xpath("//input[contains(@data-testid,'first-name-input')]");
	public static By lastName = By.xpath("//input[contains(@data-testid,'last-name-input')]");
	public static By month = By.xpath("//input[contains(@data-testid,'month-input')]");
	public static By day = By.xpath("//input[contains(@data-testid,'day-input')]");
	public static By year = By.xpath("//input[contains(@data-testid,'year-input')]");
	public static By noRadioButton = By.xpath("//input[@data-testid='add-co-applicant-no-radio-btn']");
	public static By yesRadioButton = By.xpath("//input[@data-testid='add-co-applicant-yes-radio-btn']");
	public static By quotesText = By.xpath("//*[contains(text(),'competitive quotes')]");
	public static By selectQuoteText = By.xpath("//*[contains(text(),'Select a quote to continue')]");
	public static By maritalStatus = By.xpath("//*[contains(text(),'Relationship to you')]");
	public static By personalMaritalStatus = By.xpath("//ul/li[@data-testid='Spouse-li']");
	public static By nextButton = By.xpath("//button[@data-testid='next-btn']");
	public static By disablednextButton = By.xpath("//button[@disabled and @data-testid='next-btn']");
	public static By errorMessage = By.xpath("//div[@data-testid='help-text']");

	public static WebElement yesRadioButton() {
		return driver.findElement(yesRadioButton);
	}
	
	public static WebElement noRadioButton() {
		return driver.findElement(noRadioButton);
	}
	
	public static WebElement nextButton() {
		return driver.findElement(nextButton);
	}
	
	public static WebElement quotesText() {
		return driver.findElement(quotesText);
	}
	
	public static WebElement selectQuoteText() {
		return driver.findElement(selectQuoteText);
	}
	
	public static WebElement errorMessage() {
		return driver.findElement(errorMessage);
	}
	
	public static WebElement disablednextButton() {
		return driver.findElement(disablednextButton);
	}
	
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
	
	public static void selectMaritalStatus() {
		driver.findElement(maritalStatus).click();
	}

	public static void selectPersonalMaritalStatus() {
		driver.findElement(personalMaritalStatus).click();
	}
	
}