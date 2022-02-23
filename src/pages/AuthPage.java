package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AuthPage extends BasicPage {

	private Select select;

	public AuthPage(WebDriver driver) {
		super(driver);
	}

	public WebElement getDropbox() {
		return this.driver.findElement(By.xpath("//*[@id='header']/div[2]/div/div[2]/div[2]/ul/li/a"));
	}

	public WebElement getMyAccountField() {
		return this.driver.findElement(By.xpath("//*[@class='my-account-dropdown']/ul/li/a"));
	}

	public WebElement getLogout() {
		return this.driver.findElement(By.xpath("//*[@class='my-account-dropdown']/ul/li[2]/a"));
	}

	public void logout() {
		this.getDropbox().click();
		this.getLogout().click();
	}

}
