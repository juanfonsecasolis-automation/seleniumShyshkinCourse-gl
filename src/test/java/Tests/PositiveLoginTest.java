package Tests;

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
		
		// Act
		InventoryPage inventoryPage = loginPage.login(username, password);
		
		// Assert
		Assert.assertEquals(inventoryPage.EXPECTED_PAGE_URL, inventoryPage.getUrl());
	}
	
}
