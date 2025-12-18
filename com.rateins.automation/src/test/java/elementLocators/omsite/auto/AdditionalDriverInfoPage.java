package elementLocators.omsite.auto;

import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdditionalDriverInfoPage {

	public static WebDriver driver;
	public static Properties prop;

	public AdditionalDriverInfoPage(WebDriver driver) {
		this.driver = driver;
	}

	public static By additionalDriverFirstName = By.xpath("//input[contains(@data-testid,'first-name-input')]");
	public static By additionalDriverlastName = By.xpath("//input[contains(@data-testid,'last-name-input')]");
	public static By additionalDriverMonth = By.xpath("//input[contains(@data-testid,'month-input')]");
	public static By additionalDriverDay = By.xpath("//input[contains(@data-testid,'day-input')]");
	public static By additionalDriverYear = By.xpath("//input[contains(@data-testid,'year-input')]");
	public static By additionalDriverGenderDropdown = By.xpath("//*[contains(text(),'Gender')]");
	public static By additionalDriverGender = By.xpath("//ul/li[contains(text(),'Male')]");
	public static By addDriverButton = By.xpath("//button[contains(@data-testid,'add-driver-btn')]");
	public static By addAnotherDriverRadioButton = By.xpath("//input[contains(@data-testid,'add-driver-yes-radio-btn')]");
	public static By doNotAddAnotherDriverRadioButton = By. xpath("//input[contains(@data-testid,'add-driver-no-radio-btn')]");
	
	public static void enterDriverFirstName(String driverFirstName) {
		driver.findElement(additionalDriverFirstName).sendKeys(driverFirstName);
	}

	public static void enterDriverLastName(String driverLastName) {
		driver.findElement(additionalDriverlastName).sendKeys(driverLastName);
	}

	public static void enterDriverDOBMonth(String dobMonth) {
		driver.findElement(additionalDriverMonth).sendKeys(dobMonth);
	}

	public static void enterDriverDOBDay(String dobDay) {
		driver.findElement(additionalDriverDay).sendKeys(dobDay);
	}

	public static void enterDriverDOBYear(String dobYear) {
		driver.findElement(additionalDriverYear).sendKeys(dobYear);
	}

	public static void clickGenderDropdown() {
		driver.findElement(additionalDriverGenderDropdown).click();
	}

	public static void clickGenderValue() {
		driver.findElement(additionalDriverGender).click();
	}

	public static void clickAddDriverButton() {
		driver.findElement(addDriverButton).click();
	}

	public static WebElement addAnotherDriverRadioButton() {
		return driver.findElement(addAnotherDriverRadioButton);
	}
	
	public static void clickAddAnotherDriverRadioButton() {
		driver.findElement(addAnotherDriverRadioButton).click();
	}
	
	public static WebElement doNotaddAnotherDriverRadioButton() {
		return driver.findElement(doNotAddAnotherDriverRadioButton);
	}
	
	public static void clickDoNotAddAnotherDriverRadioButton() {
		driver.findElement(doNotAddAnotherDriverRadioButton).click();
	}
}