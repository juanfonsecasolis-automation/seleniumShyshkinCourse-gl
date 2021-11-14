package Pages;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage extends BasePage {

	@FindBy(css=".inventory_item")
	List<WebElement> inventoryItems;
	
	@FindBy(css=".product_sort_container")
	WebElement orderByDropdown;
	
	By appLogoLocator = By.cssSelector(".app_logo");
	
	public InventoryPage(WebDriver driver, Logger logger) {
		super(driver,logger);
		initElements(this);
		waitForVisibilityOf(appLogoLocator);
	}
	
	public String getUrl() {
		return "https://www.saucedemo.com/inventory.html";
	}

	public List<WebElement> getInventoryItems() {
		return inventoryItems;
	}

	public WebElement getAddCartButton(WebElement item) {
		return item.findElement(By.xpath(".//button"));
	}

	public void sortFromLowToHigh(String option) {
		Select dropdown = new Select(orderByDropdown);
		dropdown.selectByVisibleText(option);
	}
	
	public double getPrice(WebElement webElement) {
		String rawPrice = webElement.findElement(By.className("inventory_item_price")).getText();
		return Double.parseDouble(rawPrice.replace("$", ""));
	}
	
}
