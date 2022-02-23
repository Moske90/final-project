package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MealPage extends BasicPage {

	private Select select;

	public MealPage(WebDriver driver) {
		super(driver);
	}

	public WebElement getQuantityInput() {
		return this.driver.findElement(By.name("product_qty"));
	}

	public WebElement getAddExtraToppings() {
		return this.driver
				.findElement(By.xpath("//*[@id='body']/section[1]/div/div/div[2]/div/div[3]/div[2]/div/div[1]"));
	}

	public WebElement ExtraToppingList(int index) {
		return this.driver.findElement(By.xpath("//*[@class='addons-scroll']/li[" + index + "]"));
	}

	public WebElement getAddToCartBtn() {
		return this.driver.findElement(By.xpath("//*[contains(@class,'js-proceedtoAddInCart')]"));
	}

	public WebElement getAddToFavourite() {
		return this.driver.findElement(By.xpath("//*[contains(@class,'favourite')]"));
	}

	public void AddToCart(String qty, int index) {
		this.getQuantityInput().sendKeys(Keys.chord(Keys.CONTROL, "a"));
		getQuantityInput().sendKeys(qty);
		try {
			getAddExtraToppings().click();
			ExtraToppingList(index).click();
		} catch (Exception e) {

		}
		getAddToCartBtn().click();
	}

	public void AddToFavourite() {
		getAddToFavourite().click();
	}

}
