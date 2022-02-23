package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultPage extends BasicPage {

	public SearchResultPage(WebDriver driver) {
		super(driver);
	}

	public List<WebElement> getAllMeals() {
		return driver.findElements(By.xpath("//*[@class='product-name']/a"));
	}

	public List<String> getNames() {
		List<String> names = new ArrayList<String>();
		for (int i = 0; i < getAllMeals().size(); i++) {
			String name = this.getAllMeals().get(i).getText();
			names.add(i, name);
		}
		return names;
	}

	public int numberOfSearchedResults() {
		return this.getAllMeals().size();
	}
}
