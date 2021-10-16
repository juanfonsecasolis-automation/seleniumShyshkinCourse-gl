package Tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.LoginPage;

public class LoginTest extends BaseTest {
	
	@Parameters({"username","password","expectedMessage"})
	@Test
	public void VerifyThatTheSystemHandlesInvalidCredentials(String username, String password, String expectedMessage) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
		String currentError = loginPage.getErrorMessage();
		Assert.assertEquals(expectedMessage, currentError);
	}
	
}
