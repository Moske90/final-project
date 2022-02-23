package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LocationPopupPage extends BasicPage {

	private JavascriptExecutor js;

	public LocationPopupPage(WebDriver driver, JavascriptExecutor js) {
		super(driver);
		this.js = js;
	}

	public WebElement getSelectLocationElement() {
		return this.driver.findElement(By.xpath("//*[@class='location-selector']/a"));
	}

	public WebElement getKeyword() {
		return this.driver.findElement(By.xpath("//*[@id='locality_keyword']"));
	}

	public WebElement getLocationItem(String locationName) {
		return this.driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}

	public WebElement getLocationInput() {
		return this.driver.findElement(By.xpath("//*[@id='location_id']"));
	}

	public WebElement getSubmit() {
		return this.driver.findElement(By.xpath("//*[@name='btn_submit']"));
	}

	public void getSelectLocationDialog() {
		this.getSelectLocationElement().click();
	}

	public void setLocation(String locationName) {
		this.getKeyword().click();
		String dataValue = this.getLocationItem(locationName).getAttribute("data-value");
		js.executeScript("arguments[0].value=arguments[1]", getLocationInput(), dataValue);
		js.executeScript("arguments[0].click();", getSubmit());
	}

	public WebElement getCloseButton() {
		return this.driver.findElement(By.xpath("//*[contains(@class, 'close-btn-white')]"));
	}

}
