package elementLocators.omsite.ho3;

import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class AddressInfoPage {

	public static WebDriver driver;
	public static Properties prop;
	public static Actions actions;
	public AddressInfoPage(WebDriver driver) {
		this.driver = driver;
		actions = new Actions(driver);
	}
	
	public static By addressText = By.xpath("//input[@data-testid='property-address-input']");
	public static By addressListDropdown = By.xpath("//ul/li[contains(text(),'1225 S Main St')]");
	public static By aptSuiteNumber = By.xpath("//input[@data-testid='apt-suite-number-input']");	

	public static By month = By.xpath("//input[contains(@data-testid,'month-input')]");
	public static By day = By.xpath("//input[contains(@data-testid,'day-input')]");
	public static By year = By.xpath("//input[contains(@data-testid,'year-input')]");
	public static By errorMessage = By.xpath("//div[@data-testid='help-text']");
	public static By nextButton = By.xpath("//button[@data-testid='next-btn']");	
	public static By disablednextButton = By.xpath("//button[@disabled and @data-testid='next-btn']");
	
	public static By city = By.xpath("//input[@data-testid='city-input']");
	public static By state = By.xpath("//div[contains(text(),'State')]");
	public static By stateDropdownValue = By.xpath("//li[@data-testid='AE-li']");
	public static By selectStateTexas = By.xpath("//li[@data-testid='TX-li']");
	public static By zipCodeText = By.xpath("//input[@data-testid='zip-input']");
	
	public static By errorMessageText = By.xpath("//*[contains(@data-testid,'error-page-header-h2')]");
	
	public static WebElement addressText() {
		return driver.findElement(addressText);
	}
	
	public static WebElement addressListDropdown() {
		return driver.findElement(addressListDropdown);
	}
	
	public static WebElement enterDOBMonth() {
		return driver.findElement(month);
	}

	public static WebElement enterDOBDay() {
		return driver.findElement(day);
	}

	public static WebElement enterDOBYear() {
		return driver.findElement(year);
	}
	
	public static WebElement nextButton() {
		return driver.findElement(nextButton);
	}
	
	public static WebElement disablednextButton() {
		return driver.findElement(disablednextButton);
	}
	
	public static WebElement errorMessage() {
		return driver.findElement(errorMessage);
	}
	
	public static WebElement aptSuiteNumber() {
		return driver.findElement(aptSuiteNumber);
	}
	
	public static WebElement city() {
		return driver.findElement(city);
	}
	
	public static WebElement state() {
		return driver.findElement(state);
	}
	
	public static WebElement stateDropdownValue() {
		return driver.findElement(stateDropdownValue);
	}
	
	public static void selectStateTexas() {
		driver.findElement(selectStateTexas).click();

	}
	
	public static WebElement zipCodeText() {
		return driver.findElement(zipCodeText);
	}
	
	public static WebElement errorMessageText() {
		return driver.findElement(errorMessageText);
	}
	
	public static void enterDataAction(WebElement element, String stringValue) {
		actions.moveToElement(element).click().sendKeys(stringValue).build().perform();
	}
	
	public static void clickDropDownAction(WebElement element) {
		actions.moveToElement(element).click().build().perform();
	}
	
}