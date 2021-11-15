package Pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

	@FindBy(css=".inventory_item_name")
	List<WebElement> itemNamesDiv;
	
	By yourCartHeaderLocator = By.cssSelector(".title");
	
	public CartPage(WebDriver driver, Logger logger) {
		super(driver,logger);
		initElements(this);
		waitForVisibilityOf(yourCartHeaderLocator);
	}

	@Override
	public String getUrl() {
		return "https://www.saucedemo.com/cart.html";
	}
	
	public List<String> getAddedItemsNames() {
		List<String> names = new ArrayList<String>();
		for(WebElement item : itemNamesDiv) {
			names.add(item.getText());
		}
		return names;
	}

}
