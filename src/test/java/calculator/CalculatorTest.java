package calculator;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import org.junit.Assert;
import utilities.ExcelUtils;

public class CalculatorTest {
	@Test
	public void calculatorTest() {
		Calculator.Operate("division", 10, 2);
		System.out.println("The result of division of 10 by 2 is " + Calculator.Operate("division", 10, 2));
	}
////// for musa
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
		
			//System.out.println("The result of the operation of "+operation+" with number "+num1+" and "+num2+ " is "+actualResult);
			
			ExcelUtils.setCellData(actualResultStr, rownum, 5);
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
		
			String now = LocalDateTime.now().toString();
			ExcelUtils.setCellData(now, rownum, 7);
				}
			
		}
	}

