package excelreadwrite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWrite {
public static void main(String[] args) throws IOException {
	String excelPath="/Users/cagrikahraman/Desktop/books.xlsx";
	FileInputStream in=new FileInputStream(excelPath);
	
	XSSFWorkbook workbook= new XSSFWorkbook(in);
	XSSFSheet worksheet=workbook.getSheet("TestData");
	
	int rowsCount=worksheet.getPhysicalNumberOfRows();
	System.out.println(rowsCount);
	in.close();
	
	XSSFCell cell=worksheet.getRow(1).getCell(2);
	if(cell==null){
		cell=worksheet.getRow(1).createCell(2);
	}
	cell.setCellValue("Fail");
	
	
	cell=worksheet.getRow(5).createCell(2);
	if(cell==null){
		cell=worksheet.getRow(5).createCell(2);
	}
	cell.setCellValue("Fail");
	FileOutputStream out=new FileOutputStream(excelPath);
	workbook.write(out);
	
	in.close();
	out.close();
		}
	
	
	
	
	

	
	
	
	
	}


