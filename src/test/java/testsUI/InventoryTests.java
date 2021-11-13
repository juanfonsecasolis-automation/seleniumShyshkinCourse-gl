package testsUI;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.InventoryPage;
import Pages.LoginPage;

public class InventoryTests extends BaseTest{

	@Parameters({"username","password"})
	@Test
	public void VerifyOnlySixItemsAreDisplayedInTheProductsWebpage(String username, String password) {
		LoginPage loginPage = new LoginPage(driver, logger);
		InventoryPage inventoryPage = loginPage.login(username, password);
		Assert.assertEquals(inventoryPage.getInventoryItems().size(), 6);
	}

	
}
