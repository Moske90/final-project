package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MealsPage extends BasicPage {

	public MealsPage(WebDriver driver) {
		super(driver);
	}

	public WebElement getSearchInput() {
		return this.driver.findElement(By.name("keywords"));
	}

	public WebElement getFiltersButton() {
		return this.driver.findElement(By.xpath("//*[@class='icn']"));
	}

	public WebElement getSortBySelector() {
		return this.driver.findElement(By.xpath("//*[contains(@class,'sort-toggle')]"));
	}

	public WebElement getSearchChefsInput() {
		return this.driver.findElement(By.name("multiple_chefs"));
	}

	public WebElement getPriceFilterMinValue() {
		return this.driver.findElement(By.name("priceFilterMinValue"));
	}

	public WebElement getPriceFilterMaxValue() {
		return this.driver.findElement(By.name("priceFilterMaxValue"));
	}

	public WebElement getSelectTypeCheckbox(String value) {
		return this.driver.findElement(By.xpath("//*[@data-id='" + value + "']"));
	}

	public WebElement getSelectMealTypeCheckbox(int value) {
		return this.driver.findElement(By.xpath("//input[@value='" + value + "']"));
	}

	public WebElement getSelectCuisineCheckbox(String cousine) {
		List<WebElement> cousines = driver
				.findElements(By.xpath("//*[@id='body']/div[2]/section[2]/div/div[1]/div[11]/div[2]/ul/li"));
		int index = 0;
		for (int i = 0; i < cousines.size(); i++) {
			if (cousines.get(i).getAttribute("cuisine-name").equals(cousine)) {
				index = i + 1;
			}
		}
		return driver.findElement(By
				.xpath("//*[@id='body']/div[2]/section[2]/div/div[1]/div[11]/div[2]/ul/li[" + index + "]/label/input"));
	}

	public WebElement selectMeal(String meal) {
		// *[@class='product-list']/div/div/div/div/div/a/img
		List<WebElement> meals = driver.findElements(By.xpath("//*[@class='product-list']/div/div/div[2]/div[2]/a"));
		int index = 0;
		for (int i = 0; i < meals.size(); i++) {
			if (meals.get(i).getText().contains(meal)) {
				index = i + 1;
			}
		}
		return driver.findElement(By.xpath("//*[@class='product-list']/div[" + index + "]/div/div/div/div/a/img"));
	}
}
