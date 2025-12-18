package utils;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestInitialize {
    public static Properties getProperties() {
        Properties properties = new Properties();
		try {
			Path configPath = Paths.get(System.getProperty("user.dir"), "/src/test/java/configuration/config.properties");
			System.out.println("Config path: " + configPath.toString());
			FileInputStream ip = new FileInputStream(configPath.toString());
			properties.load(ip);
			String headless = System.getProperty("headless");
			if(headless == null) {
				headless = "false";
			}
			properties.setProperty("headless", headless);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}	
        return properties;
    }

	public static WebDriver getChromeDriver(Properties properties) {
        String headless = properties.getProperty("headless");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        if (headless.equals("true")) {
            options.addArguments("--headless");
        }
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        return driver;
	}
}
