package Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import Utilities.DriverFactory;

public class BaseTest {

	protected WebDriver driver;
	protected Logger logger;
	
	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	public void SetUp(String browser, ITestContext ctx) {
		String testName = ctx.getCurrentXmlTest().getName();
		logger = LogManager.getLogger(testName);
		driver = DriverFactory.getDriver(browser, logger);
	}
	
	@AfterMethod(alwaysRun = true)
	public void TearDown() {
		logger.info("Closing driver...");
		driver.quit();
	}
}
