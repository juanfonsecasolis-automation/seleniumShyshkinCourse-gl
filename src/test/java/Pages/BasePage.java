package Pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
	
	protected WebDriver driver;
	final int TIMEOUT_PAGELOAD_SECS = 15;
	protected Logger logger;
	
	public BasePage(WebDriver driver, Logger logger) {
		this.driver = driver;
		this.logger = logger;
	}
	
	protected void initElements(Object childPage) {
		PageFactory.initElements(driver, childPage);
	}
	
	protected void waitForVisibilityOf(By locator) {
		// explicit wait
		new WebDriverWait(driver, TIMEOUT_PAGELOAD_SECS)
			.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public String getUrl() {
		return driver.getCurrentUrl();
	}
	
}
