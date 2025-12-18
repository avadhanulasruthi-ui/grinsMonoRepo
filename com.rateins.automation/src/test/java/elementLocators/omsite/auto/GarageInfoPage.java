package elementLocators.omsite.auto;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GarageInfoPage {

	public static WebDriver driver;
	public static Properties prop;

	public GarageInfoPage(WebDriver driver) {
		this.driver = driver;
	}

	public static By noRadioButton = By.xpath("//input[contains(@data-testid,'add-driver-no-radio-btn')]");
	public static By nextButton = By.xpath("//button[contains(@data-testid,'next-btn')]");
	public static By address = By.xpath("//input[contains(@data-testid,'garaging-address-input')]");
	public static By suiteNumber = By.xpath("//input[contains(@data-testid,'apt-suite-number-input')]");
	public static By city = By.xpath("//input[contains(@data-testid,'city-input')]");
	public static By state = By.xpath("//div[contains(@data-testid,'state-select-container')]");
	public static By selectStateTexas = By.xpath("//li[contains(@data-testid,'TX-li')]");
	public static By selectStateOhio = By.xpath("//li[contains(@data-testid,'OH-li')]");
	public static By zipCode = By.xpath("//input[contains(@data-testid,'zip-input')]");
	public static By disabledNextButton = By.xpath("//button[@disabled and contains(text(),'Next')]");

	public static WebElement noRadioButton() {
		return driver.findElement(noRadioButton);

	}
	public static WebElement cityInputField() {
		return driver.findElement(city);

	}
	public static void clickNoRadioButton() {
		driver.findElement(noRadioButton).click();

	}

	public static void clickNext() {
		driver.findElement(nextButton).click();

	}

	public static void enterAddress(String Address) {
		driver.findElement(address).sendKeys(Address);

	}

	public static void enterSuitenumber(String SuiteNumber) {
		driver.findElement(suiteNumber).sendKeys(SuiteNumber);

	}

	public static void enterCity(String City) {
		driver.findElement(city).sendKeys(City);

	}
	public static WebElement state() {
		return driver.findElement(state);

	}
	public static void selectState() {
		driver.findElement(state).click();

	}

	public static void selectStateTexas() {
		driver.findElement(selectStateTexas).click();

	}
	public static void selectStateOhio() {
		driver.findElement(selectStateOhio).click();

	}
	public static void enterzipcode(String ZipCode) {
		driver.findElement(zipCode).sendKeys(ZipCode);

	}

	public static WebElement disabledNextButton() {
		return driver.findElement(disabledNextButton);
	}

}
