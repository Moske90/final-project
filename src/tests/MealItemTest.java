package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import tests.BasicTest;

public class MealItemTest extends BasicTest {

	@Test(priority = 1)
	public void addMealToCartTest() throws InterruptedException {
		driver.navigate().to(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		popupPage.getCloseButton().click();
		Thread.sleep(500);
		mealPage.getQuantityInput().clear();
		Thread.sleep(500);
		mealPage.AddToCart("2", 1);
		Thread.sleep(1500);
		Assert.assertTrue(notificationPage.getMessage().contains("The Following Errors Occurred:"), "Assert false");
		Assert.assertTrue(notificationPage.getMessage().contains("Please Select Location"), "Assert false");

		notificationPage.alertDesapears();

		popupPage.getSelectLocationElement().click();
		popupPage.setLocation("City Center - Albany");

		Thread.sleep(500);
		mealPage.AddToCart("2", 1);

		Assert.assertTrue(notificationPage.getMessage().contains("Meal Added To Cart"), "Error");
	}

	@Test(priority = 2)
	public void addMealToFavourite() throws InterruptedException {
		driver.navigate().to(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		popupPage.getCloseButton().click();
		mealPage.AddToFavourite();
		Thread.sleep(1000);
		Assert.assertTrue(notificationPage.getMessage().contains("Please login first!"), "Assert false");
		driver.navigate().to(baseUrl + "guest-user/login-form");
		loginPage.getUsername().clear();
		loginPage.getPassword().clear();
		loginPage.login(email, password);
		driver.navigate().to(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		mealPage.AddToFavourite();
		Thread.sleep(1000);
		Assert.assertTrue(notificationPage.getMessage().contains("Product has been added to your favorites."),
				"Assert false");
	}

	@Test(priority = 3)
	public void clearCartTest() throws InterruptedException, IOException {
		driver.navigate().to(baseUrl + "meals");
		popupPage.setLocation("Butte Street Junction - Los Angeles");
		File file = new File("data/Data.xlsx");
		System.out.println(file.getAbsolutePath());
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meals");
		for (int i = 1; i < 6; i++) {
			driver.navigate().to(sheet.getRow(i).getCell(0).getStringCellValue());
			mealPage.AddToCart("2", 1);
			sa.assertTrue(notificationPage.getMessage().contains("Meal Added To Cart"), "Error");
		}
		sa.assertAll();
		Thread.sleep(500);
		cartSummaryPage.clearAll();
		Assert.assertTrue(notificationPage.getMessage().contains("All meals removed from Cart successfully"),
				"Assert false");
	}

}
