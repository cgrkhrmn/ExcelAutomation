package excelreadwrite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {
	public static void main(String[] args) throws IOException {
		String excelPath="/Users/cagrikahraman/Desktop/Bookson.xlsx";
		FileInputStream in=new FileInputStream(excelPath);
		
		XSSFWorkbook workbook= new XSSFWorkbook(in);
		
		XSSFSheet worksheet=workbook.getSheet("Sheet1");
		// find out how many rows are over there
		
		int rowsCount=worksheet.getPhysicalNumberOfRows();
		System.out.println("Number of rows is "+rowsCount);
		
		//read the first raw and first cell
		System.out.println(worksheet.getRow(0).getCell(0));
		//print second row and first cell
		System.out.println(worksheet.getRow(1).getCell(0));
		//print second row third cell
		System.out.println(worksheet.getRow(1).getCell(2));
		System.out.println(worksheet.getRow(3).getCell(1));
		
		String cellValue=worksheet.getRow(3).getCell(1).toString();
		System.out.println(cellValue);
		
		//Print all names
		int sheetRowsCount=worksheet.getPhysicalNumberOfRows();
		
		for(int row=1;row<sheetRowsCount;row++){
			String id=worksheet.getRow(row).getCell(0).toString();
			String name=worksheet.getRow(row).getCell(1).toString();
			String dep=worksheet.getRow(row).getCell(2).toString();
			System.out.println("id-->"+id+" | name-->"+name+" | dep -->"+dep);
		}
		
		
		
		
		
		
		
		
		in.close();
		
	}

}
