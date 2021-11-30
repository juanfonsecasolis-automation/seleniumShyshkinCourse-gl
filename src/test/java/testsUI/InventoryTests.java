package testsUI;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.CartPage;
import Pages.InventoryPage;
import Pages.LoginPage;

public class InventoryTests extends BaseTest{

	@Parameters({"username","password"})
	public void VerifyOnlySixItemsAreDisplayedInTheProductsWebpage(String username, String password) {
		LoginPage loginPage = new LoginPage(driver, logger);
		InventoryPage inventoryPage = loginPage.login(username, password);
		Assert.assertEquals(inventoryPage.getInventoryItems().size(), 6);
	}
	
	@Parameters({"username","password"})
	@Test(groups = {"acceptance"})
	public void VerifyAllItemsInTheInventoryPageHaveAnAddToCartButton(String username, String password) {
		LoginPage loginPage = new LoginPage(driver, logger);
		InventoryPage inventoryPage = loginPage.login(username, password);
		
		WebElement cartButton;
		for(WebElement item : inventoryPage.getInventoryItems()) {
			cartButton = inventoryPage.getAddCartButton(item);
			Assert.assertEquals(cartButton.getText(), "ADD TO CART");
		}
	}
	
	@Parameters({"username","password"})
	@Test(groups = {"acceptance"})
	public void VerifyThatTheSystemArrangesItemByPriceFromLowToHigh(String username, String password) {
		LoginPage loginPage = new LoginPage(driver, logger);
		InventoryPage inventoryPage = loginPage.login(username, password);
		
		inventoryPage.sortFromLowToHigh("Price (low to high)");
		List<WebElement> items = inventoryPage.getInventoryItems();
		
		Assert.assertTrue(inventoryPage.getPrice(items.get(0)) < inventoryPage.getPrice(items.get(1)));
	}
	
	@Parameters({"username","password"})
	@Test(groups = {"acceptance"})
	public void VerifyThatTheCartBadgeReflectsTheNumberOfItemsAddedInTheCart(String username, String password) {
		LoginPage loginPage = new LoginPage(driver, logger);
		InventoryPage inventoryPage = loginPage.login(username, password);
		List<WebElement> items = inventoryPage.getInventoryItems();
		
		inventoryPage.getAddCartButton(items.get(0)).click();
		inventoryPage.getAddCartButton(items.get(1)).click();
		
		Assert.assertEquals(Integer.parseInt(inventoryPage.getShoppingCartBadge().getText()), 2);
	}
	
	@Parameters({"username","password"})
	@Test(groups = {"acceptance"})
	public void VerifyItemsCanBeAddedToTheCart(String username, String password) {
		
		String[] expectedNames = new String[2];
		LoginPage loginPage = new LoginPage(driver, logger);
		InventoryPage inventoryPage = loginPage.login(username, password);
		List<WebElement> items = inventoryPage.getInventoryItems();
		
		inventoryPage.getAddCartButton(items.get(0)).click();
		inventoryPage.getAddCartButton(items.get(1)).click();
		
		expectedNames[0] = inventoryPage.getItemName(items.get(0));
		expectedNames[1] = inventoryPage.getItemName(items.get(1));
		
		CartPage cartPage = inventoryPage.openCart();
		List<String> currentNames = cartPage.getAddedItemsNames();
		
		for(String expectedName : expectedNames) {
			Assert.assertTrue(currentNames.contains(expectedName));
		}
		
	}
	
}
