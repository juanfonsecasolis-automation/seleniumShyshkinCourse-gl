package Tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import Utilities.DriverFactory;

public class BaseTest {

	protected WebDriver driver;
	
	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	public void SetUp(String browser) {
		driver = DriverFactory.getDriver(browser);
	}
	
	@AfterMethod(alwaysRun = true)
	public void TearDown() {
		driver.quit();
	}
}
