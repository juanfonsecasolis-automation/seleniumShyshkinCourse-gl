package Pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InventoryPage extends BasePage {

	final public static String EXPECTED_PAGE_URL = "https://www.saucedemo.com/";
	By appLogoLocator = By.cssSelector(".app_logo");
	
	public InventoryPage(WebDriver driver, Logger logger) {
		super(driver,logger);
		initElements(this);
		waitForVisibilityOf(appLogoLocator);
	}
	
}
