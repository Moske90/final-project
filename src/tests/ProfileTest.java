package tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.LocationPopupPage;
import pages.LoginPage;

public class ProfileTest extends BasicTest {

	@Test(priority = 1)
	public void profileTest() throws InterruptedException {
		driver.navigate().to(baseUrl + "guest-user/login-form");
		Thread.sleep(1000);
//		driver.switchTo().frame(driver.findElement(By.xpath("//*[contains(@class, 'model-box-mid')]")));
//		popupPage.getCloseButton().click();
//		WebElement closeBtn = popupPage.getCloseButton();
//		js.executeScript("arguments[0].click();", closeBtn);
//		wait.until(ExpectedConditions.attributeToBe(By.xpath("/html"), "class", "show-pop"));
//		driver.switchTo().activeElement().findElement(By.id("location-popup"));
		popupPage.getCloseButton().click();
		loginPage.getUsername().clear();
		loginPage.getPassword().clear();
		loginPage.login(email, password);
		Assert.assertTrue(notificationPage.getMessage().contains("Login Successfull"), "Login Unsuccessfull");

		driver.navigate().to(baseUrl + "member/profile");
		profilePage.getFirstName().clear();
		profilePage.getLastName().clear();
		profilePage.getAddress().clear();
		profilePage.getPhone().clear();
		profilePage.getZipCode().clear();
		Thread.sleep(1000);
		profilePage.changePersonalData("Nick", "Richardson", "234", "9892321273", "75025", "United States", "Missouri",
				"Blue Springs");
		Thread.sleep(1000);
		Assert.assertTrue(notificationPage.getMessage().contains("Setup Successful"), "Setup Unsuccessfull");

		authPage.logout();
		Assert.assertTrue(notificationPage.getMessage().contains("Logout Successful"), "Logout Unsuccessfull");
	}

	@Test(priority = 2)
	public void changeProfileImageTest() throws IOException, InterruptedException {
		driver.navigate().to(baseUrl + "guest-user/login-form");
		popupPage.getCloseButton().click();
		loginPage.getUsername().clear();
		loginPage.getPassword().clear();
		loginPage.login(email, password);
		Assert.assertTrue(notificationPage.getMessage().contains("Login Successfull"), "Login Unsuccessfull");

		driver.navigate().to(baseUrl + "member/profile");
		Thread.sleep(1000);
		profilePage.uploadProfilePicture("img/156_1.jpg");
		Thread.sleep(500);
		Assert.assertTrue(notificationPage.getMessage().contains("Profile Image Uploaded Successfully"), "Error");

		profilePage.removeProfilePicture();

		Assert.assertTrue(notificationPage.getMessage().contains("Profile Image Deleted Successfully"), "Error");
		notificationPage.alertDesapears();

		authPage.logout();

		Assert.assertTrue(notificationPage.getMessage().contains("Logout Successful"), "Logout Unsuccessfull");

	}

}
