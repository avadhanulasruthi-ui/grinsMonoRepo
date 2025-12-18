package elementLocators.omsite.auto;

import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VehicleInfoPage {

	public static WebDriver driver;
	public static Properties prop;

	public VehicleInfoPage(WebDriver driver) {
		this.driver = driver;
	}

	public static By nextButton = By.xpath("//button[contains(@data-testid,'next-btn')]");
	public static By vehicleInfoPageTitle = By.xpath("//*[contains(@data-testid,'vehicle-ymm-header-h2')]");
	public static By disabledNextButton = By.xpath("//button[@disabled and contains(text(),'Next')]");
	public static By yearSelect = By.xpath("//div[contains(@data-testid,'vehicle-year-select')]");
	public static By makeSelect = By.xpath("//div[contains(@data-testid,'vehicle-make-select')]");
	public static By modelSelect = By.xpath("//div[contains(@data-testid,'vehicle-model-select')]");
	public static By trimOrSubModelSelect = By.xpath("//div[contains(@data-testid,'vehicle-submodel-select')]");
	public static By vehicleOwnership = By.xpath("//div[contains(@data-testid,'vehicle-ownership-select')]");
	public static By vehicleUsage = By.xpath("//div[contains(@data-testid,'vehicle-usage-select')]");
	public static By noOfMiles = By.xpath("//input[contains(@data-testid,'vehicle-miles-input')]");
	public static By moreDetailsOfVehicle = By.xpath("//*[contains(@data-testid,'vehicle-details-header-h2')]");

	public static WebElement nextButton() {
		return driver.findElement(nextButton);
	}

	public static void clickNextButton() {
		driver.findElement(nextButton).click();
	}

	public static WebElement vehicleInfopageTitle() {
		return driver.findElement(vehicleInfoPageTitle);
	}

	public static WebElement disabledNextButton() {
		return driver.findElement(disabledNextButton);
	}

	public static void selectYear() {
		driver.findElement(yearSelect).click();

	}

	public static void selectMake() {
		driver.findElement(makeSelect).click();

	}

	public static void selectModel() {
		driver.findElement(modelSelect).click();
	}

	public static void selectTrimOrSubModel() {
		driver.findElement(trimOrSubModelSelect).click();

	}

	public static void selectVehicleOwnership() {
		driver.findElement(vehicleOwnership).click();

	}

	public static void selectVehicleUsage() {
		driver.findElement(vehicleUsage).click();
	}

	public static void enterNoOfMiles(String NoOfMiles) {
		driver.findElement(noOfMiles).sendKeys(NoOfMiles);
	}

	public static WebElement moreDetailsOfVehicle() {
		return driver.findElement(moreDetailsOfVehicle);
	}

}
