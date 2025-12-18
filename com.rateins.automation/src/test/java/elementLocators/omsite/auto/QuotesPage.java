package elementLocators.omsite.auto;

import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QuotesPage {

	public static WebDriver driver;

	public QuotesPage(WebDriver driver) {
		this.driver = driver;
	}

	public static By nextButton = By.xpath("//button[contains(@data-testid,'next-btn')]");
	public static By preparingQuotesText = By.xpath("//*[contains(@data-testid,'fetching-quotes-header-h2')]");
	public static By selectAQuoteText = By.xpath("//*[contains(@data-testid,'auto-quote-summary-subtitle-span')]");
	public static By quotesNotGeneratedText = By.xpath("//*[contains(@data-testid,'error-page-header-h2')]");

	public static WebElement nextButton() {
		return driver.findElement(nextButton);
	}

	public static WebElement preparingQuotes() {
		return driver.findElement(preparingQuotesText);
	}

	public static void clickNextButton() {
		driver.findElement(nextButton).click();
	}

	public static WebElement selectAQuote() {
		return driver.findElement(selectAQuoteText);
	}

	public static WebElement quotesNotGenerated() {
		return driver.findElement(quotesNotGeneratedText);
	}

}