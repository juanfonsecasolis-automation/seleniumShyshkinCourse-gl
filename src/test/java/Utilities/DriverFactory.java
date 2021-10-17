package Utilities;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

	public static WebDriver getDriver(String browser, Logger logger) {
		WebDriver driver;
		logger.info(String.format("Initializing driver for %s...", browser));
		switch(browser) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
				ChromeOptions options1 = new ChromeOptions();
				options1.addArguments("--headless");
				driver = new ChromeDriver(options1);
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver");
				FirefoxOptions options2 = new FirefoxOptions();
				options2.addArguments("--headless");
				driver = new FirefoxDriver(options2);
				break;
			default:
				driver = null;
		}
		return driver;
	}
	
}
