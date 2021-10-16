package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	public static WebDriver getDriver(String browser) {
		WebDriver driver;
		System.out.println(String.format("Initializing driver for %s", browser));
		switch(browser) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
				driver = new ChromeDriver();
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
				driver = new FirefoxDriver();
				break;
			default:
				System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
				driver = new ChromeDriver();
		}
		return driver;
	}
	
}
