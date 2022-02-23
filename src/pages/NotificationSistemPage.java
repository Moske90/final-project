package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSistemPage extends BasicPage {

	private WebDriverWait wait;

	public NotificationSistemPage(WebDriver driver) {
		super(driver);
	}

	private WebElement getMessageElement() {
		return this.driver.findElement(By.xpath("//*[contains(@class, 'alert--success')"
				+ " or contains(@class, 'alert--danger')][contains(@style,'display: block')]"));
	}

	public String getMessage() {
		return this.getMessageElement().getText();
	}

	public void alertDesapears() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.attributeToBe(By.xpath("//*[contains(@class, 'system_message')]"), "style",
				"display: none;"));
	}
}
