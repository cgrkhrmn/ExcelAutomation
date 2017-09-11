package simpleddt;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.CellStyle;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ExcelUtils;

public class AmazonSearchDD {
	static WebDriver driver;
	WebElement search;
	WebElement results;
	String excelFilePath = "./src/test/resources/TestData/AmazonSearchData.xlsx";

	@BeforeClass
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", "/Users/cagrikahraman/Documents/Libraries/Drivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.amazon.com");
	}

	@Test
	public void searchTest() {

		ExcelUtils.openExcelFile(excelFilePath, "Sheet1");
		int rowsCount = ExcelUtils.getUsedRowsCount();
		for (int rownum = 1; rownum < rowsCount; rownum++) {
			String execute=ExcelUtils.getCellData(rownum, 0);
			
			if(execute.equalsIgnoreCase("N")){
				ExcelUtils.setCellData(" ", rownum, 2);
				ExcelUtils.setCellData("Skip requested", rownum, 3);
			
				continue;
			}
			
			
			String searchItem = ExcelUtils.getCellData(rownum, 1);
			searchFor(searchItem);
			String resultText = getSearchResult();
			int resultCount = cleanUpResultCount(resultText);
			System.out.println(resultCount);
			ExcelUtils.setCellData(String.valueOf(resultCount), rownum, 2);
			
			
			if (resultCount > 0) {
				System.out.println("Pass");
				ExcelUtils.setCellData("Pass", rownum, 3);
				
				
			} else {
				System.out.println("Fail");
				ExcelUtils.setCellData("Fail", rownum, 3);
				
			}
			// write the time
			String now = LocalDateTime.now().toString();
			ExcelUtils.setCellData(now, rownum, 4);
				}
			}
		

		
		

	

	/**
	 * @param resultText
	 * 
	 */
	private int cleanUpResultCount(String resultText) {
		String[] arrResult = resultText.split(" ");
		int resultsCount;
		if (resultText.contains(" of ")) {
			resultsCount = Integer.parseInt(arrResult[2].replace(",", ""));
		} else {
			resultsCount = Integer.parseInt(arrResult[0]);
		}

		return resultsCount;
	}

	/**
	 * 
	 */
	private String getSearchResult() {
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("s-result-count")));
		try {
			results = driver.findElement(By.id("s-result-count"));
		} catch (NoSuchElementException noElem) {
			return "0 results";
		}
		return results.getText();
	}

	private void searchFor(String item) {
		
		search = driver.findElement(By.id("twotabsearchtextbox"));
		search.clear();
		search.sendKeys(item + Keys.ENTER);

	
	}

	@AfterClass
	public static void tearDown() {
		driver.close();
	}

}
