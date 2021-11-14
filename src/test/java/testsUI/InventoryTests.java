package testsUI;

import org.openqa.selenium.WebElement;
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
	
	@Parameters({"username","password"})
	@Test
	public void VerifyAllItemsInTheInventoryPageHaveAnAddToCartButton(String username, String password) {
		LoginPage loginPage = new LoginPage(driver, logger);
		InventoryPage inventoryPage = loginPage.login(username, password);
		
		WebElement cartButton;
		for(WebElement item : inventoryPage.getInventoryItems()) {
			cartButton = inventoryPage.getAddCartButton(item);
			Assert.assertEquals(cartButton.getText(), "ADD TO CART");
		}
	}
	
}
