package elementLocators.omsite.auto;

import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdditionalVehicleInfoPage {

	public static WebDriver driver;

	public AdditionalVehicleInfoPage(WebDriver driver) {
		this.driver = driver;
	}

	public static By additionalVehicleInfoPageTitle = By.xpath("//*[contains(@data-testid,'add-vehicle-header-h2')]");
	public static By clickNoRadioButton = By.xpath("//*[contains(@data-testid,'add-vehicle-no-radio-btn')]");
	public static By clickYesRadioButton = By.xpath("//*[contains(@data-testid,'add-vehicle-yes-radio-btn')]");
	public static By vehicleOwnershipContainerSelect = By.xpath("//*[contains(@data-testid,'vehicle-ownership-select-container')]");
	public static By vehicleOwnershipDropdownValueSelect = By.xpath("//ul[contains(@data-testid,'vehicle-ownership-select-listbox')]/li[1]");
	public static By vehicleUsageContainerSelect = By.xpath("//*[contains(@data-testid,'vehicle-usage-select-container')]");
	public static By vehicleUsageDropdownValueSelect = By.xpath("//ul[contains(@data-testid,'vehicle-usage-select-listbox')]/li[1]");

	public static WebElement additionalVehicleInfoPageTitle() {
		return driver.findElement(additionalVehicleInfoPageTitle);
	}

	public static WebElement noRadioButton() {
		return driver.findElement(clickNoRadioButton);
	}

	public static void clickNoRadioButton() {
		driver.findElement(clickNoRadioButton).click();
	}

	public static WebElement yesRadioButton() {
		return driver.findElement(clickYesRadioButton);
	}

	public static void clickYesRadioButton() {
		driver.findElement(clickYesRadioButton).click();
	}

	public static void vehicleOwnershipContainerSelect() {
		driver.findElement(vehicleOwnershipContainerSelect).click();
	}

	public static void vehicleOwnershipDropdownValueSelect() {
		driver.findElement(vehicleOwnershipDropdownValueSelect).click();
	}

	public static void vehicleUsageContainerSelect() {
		driver.findElement(vehicleUsageContainerSelect).click();
	}

	public static void vehicleUsageDropdownValueSelect() {
		driver.findElement(vehicleUsageDropdownValueSelect);
	}

}