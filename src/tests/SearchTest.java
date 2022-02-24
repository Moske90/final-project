package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class SearchTest extends BasicTest {

	@Test
	public void searchResultsTest() throws IOException, InterruptedException {
		driver.navigate().to(baseUrl + "meals");
		popupPage.setLocation("City Center - Albany");
		File file = new File("data/Data.xlsx");
		System.out.println(file.getAbsolutePath());
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meal Search Results");
		for (int i = 1; i < 7; i++) {
			driver.navigate().to(sheet.getRow(i).getCell(1).getStringCellValue());
			Thread.sleep(500);
			popupPage.getSelectLocationElement().click();
			popupPage.setLocation(sheet.getRow(i).getCell(0).getStringCellValue());
			Thread.sleep(500);
			int numberOfElements = (int) sheet.getRow(i).getCell(2).getNumericCellValue();
			sa.assertEquals(searchResultsPage.numberOfSearchedResults(), numberOfElements, "Error");
			for (int j = 0; j < searchResultsPage.getAllMeals().size(); j++) {
				sa.assertTrue(searchResultsPage.getNames().get(j)
						.contains(sheet.getRow(i).getCell(3 + j).getStringCellValue()), "Error");
			}
		}
		sa.assertAll();
	}
}
