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
		String[] options = new String[]{}; // {"--headless"};
		if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
			ChromeOptions options1 = new ChromeOptions();
			for(String option : options) {
				options1.addArguments(option);
			}
			driver = new ChromeDriver(options1);
		}else if(browser.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver");
				FirefoxOptions options2 = new FirefoxOptions();
				for(String option : options) {
					options2.addArguments(option);
				}
				driver = new FirefoxDriver(options2);
		}else {
			driver = null;
		}
		return driver;
	}
	
}
