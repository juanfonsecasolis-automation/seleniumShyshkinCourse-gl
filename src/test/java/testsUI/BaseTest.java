package testsUI;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import Utilities.DriverFactory;

public class BaseTest {

	protected WebDriver driver;
	protected Logger logger;
	
	protected String testSuiteName;
	protected String testName;
	protected String testMethodName;
	
	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)	// this is a hook
	public void SetUp(Method method, String browser, ITestContext ctx) {
		String testName = ctx.getCurrentXmlTest().getName();
		logger = LogManager.getLogger(testName);
		driver = DriverFactory.getDriver(browser, logger);
		testSuiteName = ctx.getSuite().getName();
		this.testName = testName;
		testMethodName = method.getName();
	}
	
	@AfterMethod(alwaysRun = true)	// this is a hook
	public void TearDown() {
		// TODO: for some reason the log4j is not working, find out why
		logger.info("Closing driver...");
		driver.quit();
	}
	
	protected void TakeSnapshot(String filename) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		StringBuilder pathBuilder = new StringBuilder();
		pathBuilder
			.append(System.getProperty("user.dir"))
			.append(File.separator).append("test-output")
			.append(File.separator).append("screenshots")
			.append(File.separator).append(getTodaysDate())
			.append(File.separator).append(testSuiteName)
			.append(File.separator).append(testName)
			.append(File.separator).append(testMethodName)
			.append(File.separator).append(getSystemTime())
			.append("-").append(filename).append(".png");
		try {
			FileUtils.copyFile(srcFile, new File(pathBuilder.toString()));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static String getTodaysDate() {
		return (new SimpleDateFormat("yyyyMMdd").format(new Date()));
	}
	
	private static String getSystemTime() {
		return (new SimpleDateFormat("HHmmssSSS").format(new Date()));
	}
	
}
