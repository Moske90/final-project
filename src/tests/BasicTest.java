package tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import pages.AuthPage;
import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSistemPage;
import pages.ProfilePage;
import pages.SearchResultPage;

public abstract class BasicTest {

	protected WebDriver driver;
	protected String baseUrl = "http://demo.yo-meals.com/";
	protected String email = "customer@dummyid.com";
	protected String password = "12345678a";
	protected SoftAssert sa;
	protected TakesScreenshot ts;
	protected JavascriptExecutor js;
	protected WebDriverWait wait;
	protected WebElement upload;
	protected LoginPage loginPage;
	protected LocationPopupPage popupPage;
	protected NotificationSistemPage notificationPage;
	protected ProfilePage profilePage;
	protected AuthPage authPage;
	protected MealPage mealPage;
	protected CartSummaryPage cartSummaryPage;
	protected SearchResultPage searchResultsPage;
	protected Actions action;
	protected DateTimeFormatter dtf;
	protected LocalDateTime now;

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		sa = new SoftAssert();
		ts = (TakesScreenshot) driver;
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		loginPage = new LoginPage(driver);
		popupPage = new LocationPopupPage(driver, js);
		notificationPage = new NotificationSistemPage(driver);
		profilePage = new ProfilePage(driver, js, wait);
		authPage = new AuthPage(driver);
		mealPage = new MealPage(driver);
		cartSummaryPage = new CartSummaryPage(driver);
		searchResultsPage = new SearchResultPage(driver);

		dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
		now = LocalDateTime.now();
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException, InterruptedException {
		if (result.getStatus() == ITestResult.FAILURE) {
//			System.out.println(result.getStatus());
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./screenshots/" + dtf.format(now) + "test.png"));
			Thread.sleep(1000);
		}

		driver.quit();
	}
}
