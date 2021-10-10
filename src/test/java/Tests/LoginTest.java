package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.LoginPage;

public class LoginTest extends BaseTest {
	
	@Test
	public void VerifyThatTheSystemHandlesInvalidCredentials() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("standard_user", "1234");
		String currentError = loginPage.getErrorMessage();
		Assert.assertEquals("Epic sadface: Username and password do not match any user in this service", currentError);
	}
	
}
