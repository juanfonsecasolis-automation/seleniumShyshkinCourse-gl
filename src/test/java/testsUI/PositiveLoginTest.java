package testsUI;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.InventoryPage;
import Pages.LoginPage;

public class PositiveLoginTest extends BaseTest{

	@Parameters({"username","password"})
	@Test
	public void VerifyThatTheSystemHandlesValidCredentials(String username, String password) {
		// Arrange
		LoginPage loginPage = new LoginPage(driver, logger);
		TakeSnapshot("LoginPageOpened");
		
		// Act
		InventoryPage inventoryPage = loginPage.login(username, password);
		TakeSnapshot("InventoryPageOpened");
		
		// Assert
		Assert.assertEquals(inventoryPage.getUrl(), inventoryPage.getCurrentUrl());
	}
	
}
