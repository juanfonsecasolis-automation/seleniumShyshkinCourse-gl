package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	@FindBy(id="user-name")
	WebElement usernameField;
	
	@FindBy(id="password")
	WebElement passwordField;
	
	@FindBy(id="login-button")
	WebElement loginButton;
	
	@FindBy(css="html body div#root div div.login_wrapper div.login_wrapper-inner div#login_button_container.form_column div.login-box form div.error-message-container.error")
	WebElement errorDiv;
	By errorDivLocator = By.cssSelector(".error-message-container > h3");
	
	public LoginPage(WebDriver driver) {
		super(driver);
		driver.navigate().to("https://www.saucedemo.com/");
		initElements(this, By.cssSelector(".login_logo"));
	}

	public void login(String username, String password) {
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		loginButton.click();
	}

	public String getErrorMessage() {
		return errorDiv.getText();
	}
	
}
