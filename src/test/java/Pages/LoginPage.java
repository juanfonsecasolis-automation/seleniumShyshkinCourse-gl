package Pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	final public static String EXPECTED_PAGE_URL = "https://www.saucedemo.com/";
	
	@FindBy(id="user-name")
	WebElement usernameField;
	
	@FindBy(id="password")
	WebElement passwordField;
	
	@FindBy(id="login-button")
	WebElement loginButton;
	
	By errorDivLocator2 = By.cssSelector("html body div#root div div.login_wrapper div.login_wrapper-inner div#login_button_container.form_column div.login-box form div.error-message-container.error");
	By errorDivLocator = By.cssSelector(".error-message-container > h3");
	By loginLogoLocator = By.cssSelector(".login_logo");
	
	public LoginPage(WebDriver driver, Logger logger) {
		super(driver,logger);
		driver.navigate().to(EXPECTED_PAGE_URL);
		initElements(this);
		waitForVisibilityOf(loginLogoLocator);
	}

	public InventoryPage login(String username, String password) {
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		loginButton.click();
		return new InventoryPage(driver, logger);
	}

	public String getErrorMessage() {
		return driver.findElement(errorDivLocator).getText();
	}
	
}
