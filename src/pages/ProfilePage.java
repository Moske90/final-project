package pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	private JavascriptExecutor js;
	private Select select;
	private WebDriverWait wait;

	public ProfilePage(WebDriver driver, JavascriptExecutor js, WebDriverWait wait) {
		super(driver);
		this.js = js;
		this.wait = wait;
	}

	public WebElement getFirstName() {
		return this.driver.findElement(By.name("user_first_name"));
	}

	public WebElement getLastName() {
		return this.driver.findElement(By.name("user_last_name"));
	}

	public WebElement getEmail() {
		return this.driver.findElement(By.name("user_email"));
	}

	public WebElement getAddress() {
		return this.driver.findElement(By.name("user_address"));
	}

	public WebElement getPhone() {
		return this.driver.findElement(By.name("user_phone"));
	}

	public WebElement getZipCode() {
		return this.driver.findElement(By.name("user_zip"));
	}

	public WebElement getCountry() {
		return this.driver.findElement(By.name("user_country_id"));
	}

	public WebElement getState() {
		return this.driver.findElement(By.name("user_state_id"));
	}

	public WebElement getCity() {
		return this.driver.findElement(By.name("user_city"));
	}

	public WebElement getSaveButton() {
		return this.driver.findElement(By.name("btn_submit"));
	}

	public WebElement getCurrentPassword() {
		return this.driver.findElement(By.name("current_password"));
	}

	public WebElement getNewPassword() {
		return this.driver.findElement(By.name("new_password"));
	}

	public WebElement getConfirmPassword() {
		return this.driver.findElement(By.name("conf_new_password"));
	}

	public WebElement getUploadPictureButton() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfo']/div/div[1]/div/a[1]/i"));
	}

	public WebElement getRemovePictureButton() {
		return this.driver.findElement(By.xpath("//*[@id=\"profileInfo\"]/div/div[1]/div/a[2]"));
	}

	public void preUpload() {
		js.executeScript("arguments[0].click();", getUploadPictureButton());
	}

	public void uploadProfilePicture(String address) throws IOException {
		this.preUpload();
		WebElement upload = driver.findElement(By.xpath("//input[@type='file']"));
		File img = new File(address);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(img.getCanonicalPath());
	}

	public void removeProfilePicture() {
		js.executeScript("arguments[0].click();", getRemovePictureButton());
	}

	public void changePersonalData(String firstName, String lastName, String address, String phone, String zip,
			String country, String state, String city) throws InterruptedException {
		this.getFirstName().sendKeys(firstName);
		this.getLastName().sendKeys(lastName);
		this.getAddress().sendKeys(address);
		this.getPhone().sendKeys(phone);
		this.getZipCode().sendKeys(zip);
		select = new Select(getCountry());
		select.selectByVisibleText(country);
		Thread.sleep(500);
		select = new Select(getState());
		select.selectByVisibleText(state);
		Thread.sleep(500);
		select = new Select(getCity());
		select.selectByVisibleText(city);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", getSaveButton());

	}

}
