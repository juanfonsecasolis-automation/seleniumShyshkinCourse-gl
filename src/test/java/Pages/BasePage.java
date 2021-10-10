package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

public abstract class BasePage {
	
	protected By anchorLocator; 
	protected WebDriver driver;
	final int TIMEOUT_PAGELOAD_SECS = 15;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	protected void initElements(Object childPage, By anchorLocator) {
		PageFactory.initElements(driver, childPage);
		new WebDriverWait(driver, TIMEOUT_PAGELOAD_SECS)
			.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(anchorLocator));
	}
	
}
