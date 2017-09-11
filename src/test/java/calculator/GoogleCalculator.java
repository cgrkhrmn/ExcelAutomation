package calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;
import pages.CalcPage;
import utilities.ConfigurationReader;
import utilities.Driver;
import utilities.ExcelUtils;

public class GoogleCalculator {
	static WebDriver driver;
	WebElement search;
	WebElement results;
	String excelFilePath = "./src/test/resources/TestData/Calculator.xlsx";
	CalcPage calcPage=new CalcPage();
	
	
	
	@BeforeClass
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Driver/chromedriver");
		//driver = new ChromeDriver();
		Driver.getInstance().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Driver.getInstance().get("https://www.google.com");
	}

		
	
	@Test
	public void CalculatorTest(){
	Driver.getInstance().get(ConfigurationReader.getProperty("url"));
	calcPage.searchBox.sendKeys("calculator"+Keys.ENTER);
	try{
		assertTrue(calcPage.calculator.isDisplayed());
		System.out.println("Calcualtor is displayed");
	}catch (Exception e) {
		System.out.println("Somehow calculator is NOT displayed!");
	}
	
	if(calcPage.calculatorBox.isDisplayed()){
		System.out.println("Element is visible");
	}
	
	if(calcPage.calculatorBox.isEnabled()){
		System.out.println("Element is enable");
		
	}
	
	WebDriverWait wait= new WebDriverWait(Driver.getInstance(),30);
	wait.until(ExpectedConditions.visibilityOf(calcPage.calculatorBox));
	calcPage.calculatorBox.sendKeys("3*6"+ Keys.ENTER);
	assertEquals("18", calcPage.calculatorBox.getText());
	}

	
	
	
	
	@Test
	public void dataDrivenTesting() {
		String excelFilePath = "./src/test/resources/TestData/Calculator.xlsx";

		ExcelUtils.openExcelFile(excelFilePath, "Sheet1");
		int rowsCount = ExcelUtils.getUsedRowsCount();
		for (int rownum = 1; rownum < rowsCount; rownum++) {
			String execute = ExcelUtils.getCellData(rownum, 0);
			String operation=ExcelUtils.getCellData(rownum, 1);
			Double num1=Double.valueOf(ExcelUtils.getCellData(rownum, 2));
			Double num2=Double.valueOf(ExcelUtils.getCellData(rownum, 3));
			

			if (execute.equalsIgnoreCase("N")) {
				ExcelUtils.setCellData("Skip requested", rownum, 6);
				

				continue;
			}
			
			double actualResult=Calculator.Operate(operation, num1, num2);
			String actualResultStr=String.valueOf(actualResult);
			
			//Google Calculator
			Driver.getInstance().get(ConfigurationReader.getProperty("url"));
			calcPage.searchBox.sendKeys("calculator"+Keys.ENTER);
			try{
				assertTrue(calcPage.calculator.isDisplayed());
				System.out.println("Calcualtor is displayed");
			}catch (Exception e) {
				System.out.println("Somehow calculator is NOT displayed!");
			}
			
			if(calcPage.calculatorBox.isDisplayed()){
				System.out.println("Element is visible");
			}
			
			if(calcPage.calculatorBox.isEnabled()){
				System.out.println("Element is enable");
				
			}
			
			//Date
			String now = LocalDateTime.now().toString();
			ExcelUtils.setCellData(now, rownum, 7);
			
			
			WebDriverWait wait= new WebDriverWait(Driver.getInstance(),30);
			wait.until(ExpectedConditions.visibilityOf(calcPage.calculatorBox));
			calcPage.calculatorBox.sendKeys(actualResultStr);
			String calcResult=calcPage.calculatorBox.getText();
			ExcelUtils.setCellData(calcResult, rownum, 5);
			try{
				Double expectedResult=Double.valueOf(ExcelUtils.getCellData(rownum, 4));
				if(expectedResult.equals(actualResult)){
					ExcelUtils.setCellData("Pass",rownum, 6);
					
				}else{
					ExcelUtils.setCellData("Fail",rownum, 6);
					
				}
				
			}catch (Exception e) {
				System.out.println("There is no expected result in the row number"+rownum);
				ExcelUtils.setCellData("Fail",rownum, 6);
			}
				
			
			
	

}
	}
	@AfterClass
	public static void tearDown() {
		driver.close();
	}
	}

	
